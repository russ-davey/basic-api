(ns alert-logic.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::)

(s/def ::people (s/keys :req-un [::name
                                 ::surname
                                 ::height
                                 ::age]))
(s/def ::collection-of-people (s/coll ::people))