(ns modern-clj-web.component.repo
  (:require [monger.collection :as mc]
            [monger.json]
            [system.components.mongo])
  (:import (system.components.mongo Mongo)))

(defprotocol ContactRepository
  (find-all [this]))

(extend-type Mongo
  ContactRepository
  (find-all [this]
    (mc/find-maps (:db this) "contacts")))
