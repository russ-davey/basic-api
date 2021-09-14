(ns alert-logic.data)

(def initial-data
  {:registry [{:registry-id 1
               :name "John"
               :surname "Wills"
               :profession "actor"
               :age "34"}
              {:registry-id 2
               :name "Mary"
               :surname "Stephenson"
               :profession "developer"
               :age "36"}
              {:registry-id 3
               :name "Igor"
               :surname "Cabot"
               :profession "developer"
               :age "21"}
              {:registry-id 4
               :name "Jill"
               :surname "Wise"
               :profession "waiter"
               :age "19"}]})

(def fake-db-atom (atom initial-data))