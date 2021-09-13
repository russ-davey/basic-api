(ns alert-logic.data)

(def initial-data
  {:registry [{:registry-id 1
               :name "John"
               :surname "Wills"
               :profession "actor"}
              {:registry-id 2
               :name "Mary"
               :surname "Stephenson"
               :profession "developer"}
              {:registry-id 3
               :name "Igor"
               :surname "Cabot"
               :profession "developer"}
              {:registry-id 4
               :name "Jill"
               :surname "Wise"
               :profession "waiter"}]})

(def fake-db-atom (atom initial-data))