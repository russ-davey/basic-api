(ns alert-logic.api
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
            [integrant.core :as ig]))

; TODO: GET - List record
; TODO: POST - Add record
; TODO: list - get - add - delete

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
               :swagger {:info {:title "Alert Logic"
                                :description "API for listing/getting/adding & deleting records"}}
               :handler (swagger/create-swagger-handler)}}]
       ["/ping"
        {:get {:summary "pinging"
               :handler (fn [_]
                          (log/infof "I've been pinged")
                          {:status 200
                           :body "pong"})}}]
       ["/registry"
        {:post {:summary "add record"
                :parameters {:body ::specs/api-post-body-v1}
                ;:responses {201 {:schema ::specs/api-post-response
                ;                 :description "Successfully POSTed override"
                ;                 :supplyPoint ::specs/supplyPoint
                ;                 :overrideMetadata ::specs/overrideMetadata}
                ;            400 {:schema ::specs/api-messages-response
                ;                 :description "Invalid override, see messages in the response body for more details"}}
                :handler (fn [{{:keys [data]} :body-params}]
                           (let [{:keys [messages] :as resp} {:messages "hello"
                                                              :other "it worked"}]
                             (if messages
                               {:status 400 :body resp}
                               {:status 201 :body resp})))}}]]

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