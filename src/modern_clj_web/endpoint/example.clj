(ns modern-clj-web.endpoint.example
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]))

(defn example-endpoint [config]
  (routes
    (GET "/hello" [] (response {:hello "world"}))
    (GET "/" [] "Hello World")))
