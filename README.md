
# cljs-infinite-loader

### Overview

The <strong>cljs-infinite-loader</strong> is a simple infinite loader component (Reagent) for Clojure projects.

> UI components in this library are [Reagent](https://github.com/reagent-project/reagent) components.

### deps.edn

```
{:deps {bithandshake/cljs-infinite-loader {:git/url "https://github.com/bithandshake/cljs-infinite-loader"
                                           :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-infinite-loader/tree/release).

### Documentation

The <strong>cljs-infinite-loader</strong> functional documentation is [available here](https://bithandshake.github.io/cljs-infinite-loader).

### Changelog

You can track the changes of the <strong>cljs-infinite-loader</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to use the infinite loader sensor?](#how-to-use-the-infinite-loader-sensor)

- [How to check whether the infinite loader sensor is in the viewport?](#how-to-check-whether-the-infinite-loader-sensor-is-in-the-viewport)

- [How to disable an infinite loader?](#how-to-disable-an-infinite-loader)

- [How to enable an infinite loader?](#how-to-enable-an-infinite-loader)

- [How to reload an infinite loader?](#how-to-reload-an-infinite-loader)

- [Examples](#examples)

### How to use the infinite loader sensor?

The [`infinite-loader.api/sensor`](documentation/cljs/infinite-loader/API.md#sensor)
Reagent component setups an intersection observer on itself and calls the `:on-leave`
function or the `:on-intersect` function when the sensor passes the viewport boundaries.

```
(defn my-component []
  [:div [sensor {:on-leave     (fn [] ...)
                 :on-intersect (fn [] ...)}]])
```

### How to check whether the infinite loader sensor is in the viewport?

The [`infinite-loader.api/intersecting?`](documentation/cljs/infinite-loader/API.md#intersecting)
function returns TRUE if a sensor with the given ID intersecting the viewport.

To check whether a sensor is intersecting you must provide an ID to the sensor.

```
(defn my-component []
  [:div [sensor :my-sensor {:on-leave     (fn [] ...)
                            :on-intersect (fn [] ...)}]])

(defn is-my-infinite-loader-sensor-in-the-viewport?
  []
  (intersecting? :my-sensor))                            
```

### How to disable an infinite loader?

The [`infinite-loader.api/disable!`](documentation/cljs/infinite-loader/API.md#disable)
function turns off a specific infinite loader with the given ID.

```
(defn my-component []
  [:div [sensor :my-sensor {:on-leave     (fn [] ...)
                            :on-intersect (fn [] ...)}]])

(defn disable-my-infinite-loader!
  []
  (disable! :my-sensor))                            
```

### How to enable an infinite loader?

The [`infinite-loader.api/enable!`](documentation/cljs/infinite-loader/API.md#enable)
function turns on a specific (previously disabled) infinite loader with the given ID.

```
(defn my-component []
  [:div [sensor :my-sensor {:on-leave     (fn [] ...)
                            :on-intersect (fn [] ...)}]])

(defn enable-my-infinite-loader!
  []
  (enable! :my-sensor))                            
```

### How to reload an infinite loader?

The [`infinite-loader.api/reload!`](documentation/cljs/infinite-loader/API.md#reload)
function reloads a specific infinite loader with the given ID. If the loader was in
the viewport, reloading it fires the `:on-intersect` function again.

```
(defn my-component []
  [:div [sensor :my-sensor {:on-leave     (fn [] ...)
                            :on-intersect (fn [] ...)}]])

(defn reload-my-infinite-loader!
  []
  (reload! :my-sensor))                            
```

### Examples

In the following example an infinite list of numbers rendered.

```
; The initial count of rendered numbers is 10.
;
; Storing the value in a Reagent atom makes the number-list  
; component re-rendering when the value changes.
;
(def RENDERED-NUMBER-COUNT (reagent.core/atom 10))



; The output of the number-list component looks like:
; [:<> [:div 0]
;      [:div 1]
;      [:div 2] ...]
;
(defn number-list
  []
  (letfn [(f0 [list number] (conj list [:div number]))]
         (reduce f0 [:<>] (range 0 @RENDERED-NUMBER-COUNT))))



; When the sensor gets into the viewport the 'on-intersect-f' function
; increases the rendered number count by 10.
;
; When the number list first rendered there are not enough numbers in it
; to fill the viewport and displace the sensor out of it.
; Therefore, the 'on-intersect-f' function sets a timeout for 50ms with
; the 'autocheck-f' function which reloads the infinite loader if the sensor
; is still in the viewport after the rendered number count increased and this
; little hack reloads it repeatedly until the viewport getting filled with numbers.
;
(defn infinite-number-list
  []
  (letfn [(on-intersect-f [] (swap! RENDERED-NUMBER-COUNT + 10)
                             (.setTimeout js/window autocheck-f 50))
          (autocheck-f    [] (if (intersect? :my-sensor)
                                 (reload!    :my-sensor)))]
         [:div [number-list]
               [sensor :my-sensor {:on-intersect on-intersect-f}]]))

```
