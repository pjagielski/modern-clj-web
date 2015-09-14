(ns modern-clj-web.component.repo
  (:require [com.stuartsierra.component :as component]
            [monger.collection :as mc]
            [monger.json]))

(defprotocol ContactRepository
  (find-all [this]))

(defrecord ContactComponent [mongo]
  component/Lifecycle
  (start [this] this)
  (stop [this] this)

  ContactRepository
  (find-all [this]
    (mc/find-maps (:db mongo) "contacts")))

(defn new-contact-component []
  (->ContactComponent {}))