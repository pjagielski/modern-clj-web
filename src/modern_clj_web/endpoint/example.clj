(ns modern-clj-web.endpoint.example
  (:require [clojure.java.io :as io]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer [ok]]
            [ring.util.response :refer [response]]
            [modern-clj-web.component.repo :as r]))

(defn example-endpoint [{repo :mongo}]
  (routes
    (api
      (context "/api" []
        (GET "/contacts" []
          (ok (r/find-all repo)))))
    (GET "/" [] (io/resource "public/index.html"))))
