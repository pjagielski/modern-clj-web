(ns ^:figwheel-always modern-clj-web.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]))

(enable-console-print!)

(println "hello from clojurescript")

(go
  (let [response (<! (http/get "/contacts"))]
    (println (:body response))))
