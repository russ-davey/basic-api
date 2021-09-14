(ns alert-logic.specs
  (:require [clojure.spec.alpha :as s]))

; Data input validation
(s/def ::name (s/and string? (partial re-matches #"[A-Za-z .'-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]{1,100}")))
(s/def ::surname (s/and string? (partial re-matches #"[A-Za-z .'-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð]{1,100}")))
(s/def ::height (s/and string? (partial re-matches #"[0-9]{1,3}")))
(s/def ::age (s/and string? (partial re-matches #"[0-9]{1,3}")))
(s/def ::profession (s/and string? (partial re-matches #"[A-Za-z ]{1,25}")))

(s/def ::people (s/keys :req-un [::name
                                 ::surname
                                 ::age
                                 ::profession]
                        :opt-un [::height]))
(s/def ::collection-of-people (s/coll-of ::people))

(s/def ::field string?)
(s/def ::value string?)

(s/def ::get-params (s/keys :req-un [::field
                                     ::value]))

(s/def ::registryId int?)
(s/def ::api-delete-registry-record-params (s/keys :req-un [::registryId]))