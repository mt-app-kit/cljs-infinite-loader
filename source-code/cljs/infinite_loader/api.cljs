
(ns infinite-loader.api
    (:require [infinite-loader.env          :as env]
              [infinite-loader.side-effects :as side-effects]
              [infinite-loader.views        :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; infinite-loader.env
(def intersect? env/intersect?)

; infinite-loader.side-effects
(def enable!  side-effects/enable!)
(def disable! side-effects/disable!)
(def reload!  side-effects/reload!)

; infinite-loader.views
(def sensor views/sensor)
