(ns basic-api.api
  (:require [reitit.ring :as ring]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [reitit.ring.coercion :as coercion]
            [reitit.coercion.spec]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.parameters :as parameters]
            [muuntaja.core :as m]
            [taoensso.timbre :as log]
            [basic-api.specs :as specs]
            [basic-api.db :as db]))

(def ^:private exception-middleware
  (exception/create-exception-middleware
    (merge
      exception/default-handlers
      {::exception/wrap (fn [handler e request]
                          (log/errorf e "Internal server error for API request. %s" (select-keys request [:uri :body]))
                          (handler e request))})))

(def app
  (ring/ring-handler
    (ring/router
      [["/swagger.json"
        {:get {:no-doc true
               :swagger {:info {:title "Basic API"
                                :description "API for listing/getting/adding & deleting records"}}
               :handler (swagger/create-swagger-handler)}}]
       ["/registry"
        {:post {:summary "add a record to the registry"
                :parameters {:body ::specs/collection-of-people}
                :handler (fn [{:keys [body-params]}]
                           (log/infof "POST received: %s" body-params)
                           (let [addition (db/insert-data :registry body-params)]
                             (if addition
                               {:status 201 :body addition}
                               {:status 400 :body addition})))}

         :get {:summary "get a list of all the records from the registry"
               :handler (fn [_]
                          (let [record-list (db/list-data)]
                            {:status 200
                             :body record-list}))}}]

       ["/registry/:registryId"
        {:delete {:summary "delete a record from the registry"
                  :parameters {:path ::specs/api-delete-registry-record-params}
                  :handler (fn [req]
                             (let [registry-id (get-in req [:parameters :path :registryId])
                                   deletion (db/delete-data :registry registry-id)]
                               (if (nil? deletion)
                                 {:status 404 :body {:messages ["registry ID not found or already deleted."]}}
                                 {:status 200 :body deletion})))}}]

       ["/registry/:field/:value"
        {:get {:summary "get a list of records from the registry by field and value (i.e. name = John) - case sensitive"
               :parameters {:path ::specs/get-params}
               :handler (fn [{{:keys [field value]} :path-params}]
                          (log/infof "GET by %s = %s" field value)
                          (let [record-list (db/get-by-field-and-value :registry
                                                                       (keyword field)
                                                                       value)]
                            {:status 200
                             :body record-list}))}}]]

      {:data {:coercion reitit.coercion.spec/coercion
              :muuntaja m/instance
              :middleware [parameters/parameters-middleware
                           muuntaja/format-negotiate-middleware
                           muuntaja/format-response-middleware
                           exception-middleware
                           muuntaja/format-request-middleware
                           coercion/coerce-response-middleware
                           coercion/coerce-request-middleware]}
       :conflicts nil})
    (ring/routes
      (swagger-ui/create-swagger-ui-handler
        {:path "/"
         :config {:validatorUrl nil}})
      (ring/create-default-handler))))