
(ns infinite-loader.api
    (:require [infinite-loader.env          :as env]
              [infinite-loader.side-effects :as side-effects]
              [infinite-loader.views        :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (infinite-loader.env)
(def intersect? env/intersect?)

; @redirect (infinite-loader.side-effects)
(def enable!  side-effects/enable!)
(def disable! side-effects/disable!)
(def reload!  side-effects/reload!)

; @redirect (infinite-loader.views)
(def sensor views/sensor)
