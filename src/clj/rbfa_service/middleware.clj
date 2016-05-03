(ns rbfa-service.middleware
  (:require [rbfa-service.env :refer [defaults]]
            [rbfa-service.config :refer [env]]
            [ring.middleware.flash :refer [wrap-flash]]
            [immutant.web.middleware :refer [wrap-session]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))))
