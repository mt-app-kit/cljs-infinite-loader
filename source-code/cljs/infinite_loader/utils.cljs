
(ns infinite-loader.utils
    (:require [fruits.keyword.api :as keyword]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn loader-id->observer-id
  ; @ignore
  ;
  ; @param (keyword) loader-id
  ;
  ; @example
  ; (loader-id->observer-id :my-loader)
  ; =>
  ; :my-loader--observer
  ;
  ; @return (keyword)
  [loader-id]
  (keyword/append loader-id :observer "--"))
