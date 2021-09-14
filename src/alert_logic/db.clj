(ns alert-logic.db
  (:require [alert-logic.data :as data]))

(def not-empty-seq? (comp not nil? seq))

(defn insert-data
  [table data]
  (swap! data/fake-db-atom update-in [table] conj data)
  data)

(defn list-data [] (:registry @data/fake-db-atom))

(defn get-data
  [table id]
  (let [results (filter #(= id (:registry-id %)) (table @data/fake-db-atom))]
    (when (not-empty-seq? results)
      results)))

(defn delete-data
  [table id]
  (when-let [results (get-data table id)]
    (reset! data/fake-db-atom
            (update @data/fake-db-atom table (fn [data] (remove #(= id (:registry-id %)) data))))
    results))