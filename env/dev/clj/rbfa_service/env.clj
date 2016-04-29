(ns rbfa-service.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [rbfa-service.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[rbfa-service started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[rbfa-service has shutdown successfully]=-"))
   :middleware wrap-dev})
