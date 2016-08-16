(defproject modern-clj-web "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 ;;  frontend
                 [cljs-http "0.1.37"]
                 [org.omcljs/om "0.9.0"]
                 [prismatic/om-tools "0.4.0"]
                 [racehub/om-bootstrap "0.6.1"]
                 [jkkramer/verily "0.6.0"]
                 ;; backend
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.1"]
                 [duct "0.4.2"]
                 [meta-merge "0.1.1"]
                 [prismatic/schema "1.1.3"]
                 [metosin/compojure-api "1.1.6"]
                 [environ "1.0.3"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-jetty-component "0.3.1"]
                 [org.danielsz/system "0.1.9"]
                 [com.novemberain/monger "2.0.0"]]
  :plugins [[lein-environ "1.0.3"]
            [lein-figwheel "0.5.4-7"]]
  :generators [[duct/generators "0.3.0"]]
  :duct {:ns-prefix modern-clj-web}
  :main ^:skip-aot modern-clj-web.main
  :profiles
    {:dev  [:project/dev  :profiles/dev]
     :test [:project/test :profiles/test]
     :repl {:resource-paths ^:replace ["resources" "target/figwheel"]
            :prep-tasks     ^:replace [["compile"]]}
     :uberjar {:aot :all}
     :profiles/dev  {}
     :profiles/test {}
     :project/dev   {:source-paths ["dev"]
                     :repl-options {:init-ns user}
                     :dependencies [[reloaded.repl "0.2.2"]
                                    [org.clojure/tools.namespace "0.2.11"]
                                    [org.clojure/tools.nrepl "0.2.12"]
                                    [kerodon "0.7.0"]]
                     :env {:port 3000}}
     :project/test  {}}
  :cljsbuild
    {:builds [{:id "dev"
               :source-paths ["src-cljs"]
               :figwheel true
               :compiler {:main       "modern-clj-web.core"
                          :asset-path "js/out"
                          :output-to  "target/figwheel/public/js/modern-clj-web.js"
                          :output-dir "target/figwheel/public/js/out"}}]})
