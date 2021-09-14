(ns alert-logic.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::surname string?)
(s/def ::height int?)
(s/def ::age int?)
(s/def ::profession string?)

(s/def ::people (s/keys :req-un [::name
                                 ::surname
                                 ::age
                                 ::profession]
                        :opt-un [::height]))
(s/def ::collection-of-people (s/coll-of ::people))

(s/def ::registryId int?)
(s/def ::api-delete-registry-record-params (s/keys :req-un [::registryId]))