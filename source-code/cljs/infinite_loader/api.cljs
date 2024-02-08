
(ns infinite-loader.api
    (:require [infinite-loader.env          :as env]
              [infinite-loader.side-effects :as side-effects]
              [infinite-loader.views        :as views]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (infinite-loader.env/*)
(def loader-enabled?   env/loader-enabled?)
(def loader-disabled?  env/loader-disabled?)
(def loader-intersect? env/loader-intersect?)

; @redirect (infinite-loader.side-effects/*)
(def enable-loader!  side-effects/enable-loader!)
(def disable-loader! side-effects/disable-loader!)
(def reload-loader!  side-effects/reload-loader!)

; @redirect (infinite-loader.views/*)
(def sensor views/sensor)
