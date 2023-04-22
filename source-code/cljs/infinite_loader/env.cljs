
(ns infinite-loader.env
    (:require [infinite-loader.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn observer-disabled?
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ;
  ; @return (boolean)
  [loader-id]
  (get-in @state/OBSERVERS [loader-id :disabled?]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn intersect?
  ; @param (keyword) loader-id
  ;
  ; @usage
  ; (intersect? :my-loader)
  ;
  ; @return (boolean)
  [loader-id]
  (get-in @state/OBSERVERS [loader-id :intersect?]))
