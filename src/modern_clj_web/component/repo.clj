(ns modern-clj-web.component.repo
  (:require [monger.collection :as mc]
            [monger.json]))

(defprotocol ContactRepository
  (find-all [this]))

(defrecord ContactRepoComponent [mongo]
  ContactRepository
  (find-all [this]
    (mc/find-maps (:db mongo) "contacts")))

(defn new-contact-repo-component []
  (->ContactRepoComponent {}))
