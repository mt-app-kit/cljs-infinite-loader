
(ns infinite-loader.views
    (:require [fruits.hiccup.api         :as hiccup]
              [fruits.random.api         :as random]
              [infinite-loader.env       :as env]
              [infinite-loader.state     :as state]
              [infinite-loader.utils     :as utils]
              [intersection-observer.api :as intersection-observer]
              [reagent.core              :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn- infinite-loader
  ; @ignore
  ;
  ; @param (keyword) loader-id
  [loader-id]
  [:div {:class :infinite-loader  :id (hiccup/value loader-id)}
        [:div {:id (-> loader-id utils/loader-id->observer-id hiccup/value)
               :style (if (env/observer-disabled? loader-id)
                          {:position "fixed" :bottom "-100px"})}]])

(defn sensor
  ; @param (keyword)(opt) loader-id
  ; @param (map) loader-props
  ; {:on-intersect (function)(opt)
  ;  :on-leave (function)(opt)}
  ;
  ; @usage
  ; [sensor {:on-intersect (fn [] ...)
  ;          :on-leave     (fn [] ...)}]
  ([loader-props]
   [sensor (random/generate-keyword) loader-props])

  ([loader-id {:keys [on-intersect on-leave] :as loader-props}]
   (let [element-id (-> loader-id utils/loader-id->observer-id hiccup/value)
         callback-f (fn [%] (swap! state/OBSERVERS assoc-in [loader-id :intersect?] %)
                            (if % (if on-intersect (on-intersect))
                                  (if on-leave     (on-leave))))]
        (reagent/create-class {:component-did-mount    (fn [] (intersection-observer/setup-observer!  element-id callback-f))
                               :component-will-unmount (fn [] (intersection-observer/remove-observer! element-id)
                                                              (swap! state/OBSERVERS dissoc loader-id))
                               :reagent-render         (fn [] [infinite-loader loader-id])}))))
