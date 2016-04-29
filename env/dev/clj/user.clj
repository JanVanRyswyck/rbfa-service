(ns user
  (:require [mount.core :as mount]
            rbfa-service.core))

(defn start []
  (mount/start-without #'rbfa-service.core/repl-server))

(defn stop []
  (mount/stop-except #'rbfa-service.core/repl-server))

(defn restart []
  (stop)
  (start))


