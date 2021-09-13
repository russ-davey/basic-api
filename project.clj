(defproject alert-logic "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 ;; Dependency injection
                 [integrant "0.8.0"]

                 ;; Rest API
                 [ring/ring-core "1.7.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [metosin/reitit "0.5.6"]
                 [metosin/reitit-swagger-ui "0.5.6"]
                 [metosin/compojure-api "1.1.13"]

                 ;; Logging
                 [com.taoensso/timbre "5.1.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler alert-logic.api/app}
  :repl-options {:init-ns alert-logic.core}
  :main ^:skip-aot alert-logic.core)
