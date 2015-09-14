(ns modern-clj-web.endpoint.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [ring.util.response :refer [response]]
            [modern-clj-web.component.repo :as r]))

(defn example-endpoint [{repo :contact-repo}]
  (routes
    (GET "/contacts" []
      (response (r/find-all repo)))
    (GET "/" [] (io/resource "public/index.html"))))
