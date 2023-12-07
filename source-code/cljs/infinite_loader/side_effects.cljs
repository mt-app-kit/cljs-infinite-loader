
(ns infinite-loader.side-effects
    (:require [infinite-loader.state :as state]
              [time.api              :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn enable!
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (enable! :my-loader)
  [loader-id]
  (swap! state/OBSERVERS assoc-in [loader-id :disabled?] false))

(defn disable!
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (disable! :my-loader)
  [loader-id]
  (swap! state/OBSERVERS assoc-in [loader-id :disabled?] true))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reload!
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (reload! :my-loader)
  [loader-id]
  ; Placing the observer out of the viewport then restoring its position triggers the callback function.
  ;
  ; If the observer placed out of the viewport for a too short while (e.g., 5ms),
  ; the callback function couldn't be triggered.
  (letfn [(enable-f  [] (enable!  loader-id))
          (disable-f [] (disable! loader-id))]
         (disable-f)
         (time/set-timeout! enable-f 50)))
