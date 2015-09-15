(ns ^:figwheel-always modern-clj-web.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]
            [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(enable-console-print!)

(println "hello from clojurescript")

(defn get-contacts []
  (go
    (let [response (<! (http/get "/contacts"))]
      (:body response))))

(def app-state (atom {:message "hello from om"}))

(defcomponent contact-comp [contact _]
  (render [_]
    (dom/li (str (:first_name contact) " " (:last_name contact)))))

(defcomponent app [data _]
  (will-mount [_]
    (go
      (let [contacts (<! (get-contacts))]
        (om/update! data :contacts contacts))))
  (render [_]
    (dom/div
      (dom/h2 (:message data))
      (dom/ul
        (om/build-all contact-comp (:contacts data))))))

(om/root app app-state
         {:target (.getElementById js/document "main")})