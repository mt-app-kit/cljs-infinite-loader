
(ns infinite-loader.state
    (:require [reagent.core :refer [atom] :rename {atom ratom}]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @ignore
;
; @constant (map)
(def OBSERVERS (ratom {}))
