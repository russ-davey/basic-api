(ns alert-logic.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::surname string?)
(s/def ::height string?)
(s/def ::age string?)
(s/def ::profession string?)

(s/def ::people (s/keys :req-un [::name
                                 ::surname
                                 ::age
                                 ::profession]
                        :opt-un [::height]))
(s/def ::collection-of-people (s/coll-of ::people))

;(s/def ::value (s/or string? int?))
(s/def ::field string?)
(s/def ::value string?)

(s/def ::get-params (s/keys :req-un [::field
                                     ::value]))

(s/def ::registryId int?)
(s/def ::api-delete-registry-record-params (s/keys :req-un [::registryId]))