
(ns infinite-loader.views
    (:require [fruits.random.api         :as random]
              [infinite-loader.env       :as env]
              [infinite-loader.utils     :as utils]
              [intersection-observer.api :as intersection-observer]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor
  ; @description
  ; Infinite loader component with intersection observer.
  ;
  ; @param (keyword)(opt) loader-id
  ; @param (map) loader-props
  ; {:on-enter-f (function)(opt)
  ;  :on-leave-f (function)(opt)}
  ;
  ; @usage
  ; [sensor {...}]
  ;
  ; @usage
  ; [sensor :my-loader {...}]
  ;
  ; @usage
  ; [sensor :my-loader {:on-enter-f (fn [loader-id] ...)
  ;                     :on-leave-f (fn [loader-id] ...)}]
  ([loader-props]
   [sensor (random/generate-keyword) loader-props])

  ([loader-id loader-props]
   (if-not (env/loader-disabled? loader-id)
           [intersection-observer/sensor loader-id {:callback-f (utils/sensor-intersect-f loader-id loader-props)}])))
