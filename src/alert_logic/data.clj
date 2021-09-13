(ns alert-logic.data)

(def initial-data
  {:registry [{:name "John"
                :surname "Wills"
                :profession "actor"}
               {:name "Mary"
                :surname "Stephenson"
                :profession "developer"}
               {:name "Igor"
                :surname "Cabot"
                :profession "developer"}
               {:name "Jill"
                :surname "Wise"
                :profession "waiter"}]})

(def fake-db-atom (atom initial-data))