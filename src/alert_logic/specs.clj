(ns alert-logic.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::surname string?)
(s/def ::height int?)
(s/def ::age int?)

(s/def ::people (s/keys :req-un [::name
                                 ::surname
                                 ::height
                                 ::age]))
(s/def ::collection-of-people (s/coll-of ::people))