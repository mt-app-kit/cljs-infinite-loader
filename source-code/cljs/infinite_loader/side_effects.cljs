
(ns infinite-loader.side-effects
    (:require [infinite-loader.state :as state]
              [time.api              :as time]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn enable-loader!
  ; @description
  ; Re-enables the loader (identified by the given loader ID).
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (enable-loader! :my-loader)
  [loader-id]
  (swap! state/LOADERS assoc-in [loader-id :disabled?] false))

(defn disable-loader!
  ; @description
  ; Disables the loader (identified by the given loader ID).
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (disable-loader! :my-loader)
  [loader-id]
  (swap! state/LOADERS assoc-in [loader-id :disabled?] true))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reload-loader!
  ; @description
  ; Reloads the loader (identified by the given loader ID), and triggers the ':on-enter-f'
  ; or ':on-leave-f' function, depending on the sensor position.
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (reload-loader! :my-loader)
  [loader-id]
  ; - Placing the observer outside of the viewport, then restoring its position triggers the callback function.
  ; - If the observer placed outside of the viewport for a too short while (e.g., 5ms), the callback function wouldn't fire.
  (letfn [(enable-f  [] (enable-loader!  loader-id))
          (disable-f [] (disable-loader! loader-id))]
         (disable-f)
         (time/set-timeout! enable-f 50)))
