(ns modern-clj-web.config
  (:require [environ.core :refer [env]]))

(def defaults
  {:http {:port 3000}
   :mongo-uri "mongodb://localhost:27017/contacts"})

(def environ
  {:http {:port (some-> env :port Integer.)}})
