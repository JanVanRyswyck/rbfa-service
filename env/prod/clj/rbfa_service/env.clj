(ns rbfa-service.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[rbfa-service started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[rbfa-service has shutdown successfully]=-"))
   :middleware identity})
