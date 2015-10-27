(ns modern-clj-web.system
  (:require [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.middleware.not-found :refer [wrap-not-found]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [modern-clj-web.endpoint.example :refer [example-endpoint]]
            [modern-clj-web.component.repo]
            [system.components.mongo :refer [new-mongo-db]]))

(def base-config
  {:app {:middleware [[wrap-not-found :not-found]
                      [wrap-json-body :json]
                      [wrap-json-response]
                      [wrap-defaults :defaults]]
         :not-found  "Resource Not Found"
         :json       {:keywords? true}
         :defaults   (meta-merge site-defaults {})}})

(defn new-system [config]
  (let [config (meta-merge base-config config)]
    (-> (component/system-map
         :app  (handler-component (:app config))
         :http (jetty-server (:http config))
         :example (endpoint-component example-endpoint)
         :mongo (new-mongo-db (:mongo-uri config)))
        (component/system-using
         {:http [:app]
          :app  [:example]
          :example [:mongo]}))))