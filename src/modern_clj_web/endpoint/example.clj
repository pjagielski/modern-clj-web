(ns modern-clj-web.endpoint.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [ring.util.response :refer [response]]))

(defn example-endpoint [config]
  (routes
    (GET "/hello" [] (response {:hello "world"}))
    (GET "/" [] (io/resource "public/index.html"))))
