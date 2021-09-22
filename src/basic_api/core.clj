(ns basic-api.core
  (:require [ring.adapter.jetty :as ring]
            [basic-api.api :as api]))

(defn -main
  [& args]
  (ring/run-jetty api/app {:port 8080
                           :join? false}))