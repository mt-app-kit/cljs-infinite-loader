
(ns infinite-loader.state
    (:require [reagent.core :refer [atom] :rename {atom ratom}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @atom (map)
; {:my-loader (map)
;   {:disabled? (boolean)
;    :intersect? (boolean)}}
(def LOADERS (ratom {}))
