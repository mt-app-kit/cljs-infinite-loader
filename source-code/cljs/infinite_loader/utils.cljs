
(ns infinite-loader.utils
    (:require [infinite-loader.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn sensor-intersect-f
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:on-enter-f (function)(opt)
  ;  :on-leave-f (function)(opt)}
  ;
  ; @usage
  ; (sensor-intersect-f :my-loader {...})
  ; =>
  ; (fn [intersect?] ...)
  ;
  ; @return (function)
  [loader-id {:keys [on-enter-f on-leave-f]}]
  (fn [intersect?]
      (swap! state/LOADERS assoc-in [loader-id :intersect?] intersect?)
      (if intersect? (if on-enter-f (on-enter-f loader-id))
                     (if on-leave-f (on-leave-f loader-id)))))
