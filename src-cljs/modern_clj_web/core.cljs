(ns ^:figwheel-always modern-clj-web.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]
            [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [om-bootstrap.input :as i]
            [jkkramer.verily :as v]))

(enable-console-print!)

(println "hello from clojurescript")

(def contact-validations
  [[:required [:first-name :last-name]]
   [:min-length 3 [:first-name :last-name]]])

(defn to-set [seq]
  (into #{} seq))

(defn get-validation-messages [validation field]
  (->>
    validation
    (filter (fn [m] (contains? (to-set (:keys m)) field)))
    (map :msg)))

(defn empty-contact []
  {:first-name "" :last-name ""})

(defn get-contacts []
  (go
    (let [response (<! (http/get "/api/contacts"))]
      (:body response))))

(def app-state (atom {:message "hello from om"}))

(defcomponent contact-view [contact _]
  (render [_]
    (dom/li (str (:firstname contact) " " (:lastname contact)))))

(defn handle-change [e owner field]
  (let [value (.. e -target -value)]
    (om/set-state! owner [:contact field] value)
    (om/set-state! owner :validation
                   (v/validate (om/get-state owner :contact) contact-validations))))

(defn text-field [field label state owner]
  (let [validation-msgs (get-validation-messages (:validation state) field)]
    (i/input
      {:type      "text"
       :help      (clojure.string/join ", " validation-msgs)
       :value     (get-in state [:contact field])
       :label     label
       :bs-style  (if (empty? validation-msgs) "success" "error")
       :on-change #(handle-change % owner field)})))

(defcomponent new-contact-view [_ owner]
  (init-state [_] {:contact (empty-contact)
                   :validation (v/validate (empty-contact) contact-validations)})
  (render-state [_ state]
    (dom/form
      (dom/h3 "New contact")
      (text-field :first-name "First name" state owner)
      (text-field :last-name "Last name" state owner)
      (dom/span (str state)))))

(defcomponent app [data _]
  (will-mount [_]
    (go
      (let [contacts (<! (get-contacts))]
        (om/update! data :contacts contacts))))
  (render [_]
    (dom/div {:class "col-md-4"}
      (dom/h2 (:message data))
      (dom/ul
        (om/build-all contact-view (:contacts data)))
      (om/build new-contact-view data))))

(om/root app app-state
         {:target (.getElementById js/document "main")})