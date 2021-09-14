(ns alert-logic.db-test
  (:require [clojure.test :refer :all]
            [alert-logic.db :as target]
            [alert-logic.data :as data]))

(defn- reset-data [] (reset! data/fake-db-atom data/initial-data))

(deftest db-crud
  (reset-data)
  (testing "reading data from database"
    (is (= data/initial-data {:registry (target/list-data)})))

  (reset-data)
  (testing "inserting data into database"
    (let [test-data {:registry-id 5
                     :name "Test"
                     :surname "Man"
                     :profession "Tester"}
          expected-data (update @data/fake-db-atom :registry #(conj % test-data))
          _ (target/insert-data :registry test-data)]
      (is (= @data/fake-db-atom
             expected-data) )))

  (reset-data)
  (testing "deleting row from database"
    (let [expected-data (update @data/fake-db-atom :registry (fn [data] (remove #(= 4 (:registry-id %)) data)))
          _ (target/delete-data :registry 4)]
      (is (= @data/fake-db-atom
             expected-data) ))))