(defproject modern-clj-web "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 ;;  frontend
                 [cljs-http "0.1.37"]
                 [org.omcljs/om "0.9.0"]
                 [prismatic/om-tools "0.3.12"]
                 [racehub/om-bootstrap "0.5.0"]
                 [jkkramer/verily "0.6.0"]
                 ;; backend
                 [com.stuartsierra/component "0.2.3"]
                 [compojure "1.4.0"]
                 [duct "0.3.0"]
                 [environ "1.0.0"]
                 [meta-merge "0.1.1"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-jetty-component "0.2.2"]
                 [ring/ring-json "0.3.1"]
                 [cheshire "5.1.1"]
                 [org.danielsz/system "0.1.9"]
                 [com.novemberain/monger "2.0.0"]]
  :plugins [[lein-environ "1.0.0"]
            [lein-gen "0.2.2"]
            [lein-figwheel "0.3.9"]]
  :generators [[duct/generators "0.3.0"]]
  :duct {:ns-prefix modern-clj-web}
  :main ^:skip-aot modern-clj-web.main
  :target-path "target/%s/"
  :aliases {"gen"   ["generate"]
            "setup" ["do" ["generate" "locals"]]}
  :profiles
    {:dev  [:project/dev  :profiles/dev]
     :test [:project/test :profiles/test]
     :uberjar {:aot :all}
     :profiles/dev  {}
     :profiles/test {}
     :project/dev   {:source-paths ["dev"]
                     :repl-options {:init-ns user}
                     :dependencies [[reloaded.repl "0.1.0"]
                                    [org.clojure/tools.namespace "0.2.11"]
                                    [kerodon "0.6.1"]]
                     :env {:port 3000}}
     :project/test  {}}
  :cljsbuild
    {:builds [{:id "dev"
               :source-paths ["src-cljs"]
               :figwheel true
               :compiler {:main       "modern-clj-web.core"
                          :asset-path "js/out"
                          :output-to  "resources/public/js/modern-clj-web.js"
                          :output-dir "resources/public/js/out"}}]})
