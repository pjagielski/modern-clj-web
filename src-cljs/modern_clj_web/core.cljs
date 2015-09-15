(ns ^:figwheel-always modern-clj-web.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]
            [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(enable-console-print!)

(println "hello from clojurescript")

(go
  (let [response (<! (http/get "/contacts"))]
    (println (:body response))))

(def app-state (atom {:message "hello from om"}))

(defcomponent app [data owner]
  (render [_]
    (dom/div (:message data))))

(om/root app app-state
         {:target (.getElementById js/document "main")})