(ns rbfa-service.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [rbfa-service.layout :refer [error-page]]
            [rbfa-service.routes.home :refer [home-routes]]
            [rbfa-service.routes.services :refer [service-routes]]
            [compojure.route :as route]
            [rbfa-service.env :refer [defaults]]
            [mount.core :as mount]
            [rbfa-service.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    #'service-routes
    (-> #'home-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
