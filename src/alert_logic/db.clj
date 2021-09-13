(ns alert-logic.db
  (:require [alert-logic.data :as data]))

(defn list-data
  []
  (:registry @data/fake-db-atom))