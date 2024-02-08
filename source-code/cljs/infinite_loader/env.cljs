
(ns infinite-loader.env
    (:require [infinite-loader.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn loader-enabled?
  ; @description
  ; Returns TRUE if the loader (identified by the given loader ID) is enabled.
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (loader-enabled? :my-loader)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [loader-id]
  (-> (get-in @state/LOADERS [loader-id :disabled?]) not))

(defn loader-disabled?
  ; @description
  ; Returns TRUE if the loader (identified by the given loader ID) is disabled.
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (loader-disabled? :my-loader)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [loader-id]
  (get-in @state/LOADERS [loader-id :disabled?]))

(defn loader-intersects?
  ; @description
  ; Returns TRUE if the loader (identified by the given loader ID) intersects the viewport.
  ;
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (loader-intersects? :my-loader)
  ; =>
  ; true
  ;
  ; @return (boolean)
  [loader-id]
  (get-in @state/LOADERS [loader-id :intersect?]))
