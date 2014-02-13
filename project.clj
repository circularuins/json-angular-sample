(defproject json-angular-sample "0.1.0-SNAPSHOT"
  :description "Example and experiment of Clojure web application by Angular.js"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [compojure "1.1.5"]
                 [cheshire "4.0.0"]
                 [clj-http "0.6.5"]]
  :dev-dependencies [[ring/ring-devel "1.1.0"]]
  :plugins [[lein-ring "0.8.3"]]
  :main json-angular-sample.core
  :ring {:handler json-angular-sample.core/app})
