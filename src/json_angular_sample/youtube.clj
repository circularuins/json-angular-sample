(ns json-angular-sample.youtube
  (:require [clj-http.client :as http]))

(def API "http://gdata.youtube.com/feeds/api/videos")

(defn get-movies [word]
  (let [response (http/get API {:query-params {:vq word :start-index 1 :max-results 30 :alt "json" :v 2} :as :json})]
    (((response :body) :feed) :entry)))
