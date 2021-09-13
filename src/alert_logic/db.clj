(ns alert-logic.db
  (:require [alert-logic.data :as data]))

(defn insert-data
  [table data]
  (swap! data/fake-db-atom update-in [table] conj data)
  data)

(defn delete-data
  [])

(defn list-data
  []
  (:registry @data/fake-db-atom))