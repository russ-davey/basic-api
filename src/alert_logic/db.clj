(ns alert-logic.db
  (:require [alert-logic.data :as data]))

(def not-empty-seq? (comp not nil? seq))

(defn get-last-registry-id [] (->> @data/fake-db-atom
                                   :registry
                                   (sort-by :registry-id)
                                   last
                                   :registry-id))

(def index-atom (atom (get-last-registry-id)))

(defn insert-data
  [table data]
  (doseq [new-row data]
    (swap! index-atom inc)
    (let [new-data (assoc new-row :registry-id @index-atom)]
      (swap! data/fake-db-atom update-in [table] conj new-data)
      new-data)))

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