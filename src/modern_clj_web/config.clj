(ns modern-clj-web.config
  (:require [environ.core :refer [env]]))

(def defaults
  ^:displace {:http {:port 3000}})

(def environ
  {:http {:port (some-> env :port Integer.)}
   :mongo-uri "mongodb://192.168.99.100/contacts"})
