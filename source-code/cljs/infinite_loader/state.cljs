
(ns infinite-loader.state
    (:require [reagent.core :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @atom (map)
; {:my-loader (map)
;   {:disabled? (boolean)
;    :intersect? (boolean)}}
(def LOADERS (reagent/atom {}))
