(ns json-angular-sample.core
  (:use [compojure.core]
        [ring.util.response]
        [ring.middleware.content-type]
        [ring.middleware.params])
  (:require [ring.adapter.jetty :as jetty]
            [cheshire.core :as json]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [json-angular-sample.youtube :as youtube])
  (:gen-class :main true))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes api-routes
  (GET "/api/movies/:word" [word]
       (json-response {:word word :movies (youtube/get-movies word)}))
  (route/resources "/")
  (route/not-found "Page not found"))

(defn wrap-dir-index
  "Middleware to force request for / to return index.html"
  [handler]
  (fn [req]
    (handler (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

(def app (-> api-routes
             wrap-params
             wrap-content-type
             wrap-dir-index
             handler/site))

(defn -main
  [& args]
  (let [port (or (first *command-line-args*) 8080)]
    (jetty/run-jetty app {:port port})))
