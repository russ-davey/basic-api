(ns alert-logic.core
  (:require [ring.adapter.jetty :as ring]
            [alert-logic.api :as api]))

(defn -main
  [& args]
  (ring/run-jetty api/app {:port 8080
                           :join? false}))