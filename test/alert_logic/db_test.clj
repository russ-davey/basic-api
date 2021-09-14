(ns alert-logic.db-test
  (:require [clojure.test :refer :all]
            [alert-logic.db :as target]
            [alert-logic.data :as data]
            [alert-logic.db :as db]))

(defn- reset-data []
  (reset! data/fake-db-atom data/initial-data)
  (reset! db/index-atom (db/get-last-registry-id)))

(deftest db-crud
  (reset-data)
  (testing "reading data from database"
    (is (= data/initial-data {:registry (target/list-data)})))

  (testing "reading from the database for a registry-id"
    (is (= {:registry-id 2
            :name "Mary"
            :age "36"
            :surname "Stephenson"
            :profession "developer"}
           (first (target/get-data :registry 2)))))

  (testing "reading from the database by field and value"
    (is (= [{:age "36"
             :name "Mary"
             :profession "developer"
             :registry-id 2
             :surname "Stephenson"}
            {:age "21"
             :name "Igor"
             :profession "developer"
             :registry-id 3
             :surname "Cabot"}]
           (target/get-by-field-and-value :registry :profession "developer"))))

  (reset-data)
  (testing "inserting data into database"
    (let [test-data [{:name "Test"
                      :surname "Man"
                      :profession "Tester"}]
          expected-data (update @data/fake-db-atom :registry #(conj % (assoc (first test-data) :registry-id 5)))
          _ (target/insert-data :registry test-data)]
      (is (= @data/fake-db-atom
             expected-data))))

  (reset-data)
  (testing "deleting row from database"
    (let [expected-data (update @data/fake-db-atom :registry (fn [data] (remove #(= 4 (:registry-id %)) data)))
          _ (target/delete-data :registry 4)]
      (is (= @data/fake-db-atom
             expected-data)))))