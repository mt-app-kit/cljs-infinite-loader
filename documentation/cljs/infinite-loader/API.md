
# infinite-loader.api ClojureScript namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > infinite-loader.api

### Index

- [disable!](#disable)

- [enable!](#enable)

- [intersect?](#intersect)

- [reload!](#reload)

- [sensor](#sensor)

### disable!

```
@param (keyword) loader-id
```

```
@usage
(disable! :my-loader)
```

<details>
<summary>Source code</summary>

```
(defn disable!
  [loader-id]
  (swap! state/OBSERVERS assoc-in [loader-id :disabled?] true))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [infinite-loader.api :refer [disable!]]))

(infinite-loader.api/disable! ...)
(disable!                     ...)
```

</details>

---

### enable!

```
@param (keyword) loader-id
```

```
@usage
(enable! :my-loader)
```

<details>
<summary>Source code</summary>

```
(defn enable!
  [loader-id]
  (swap! state/OBSERVERS assoc-in [loader-id :disabled?] false))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [infinite-loader.api :refer [enable!]]))

(infinite-loader.api/enable! ...)
(enable!                     ...)
```

</details>

---

### intersect?

```
@param (keyword) loader-id
```

```
@usage
(intersect? :my-loader)
```

```
@return (boolean)
```

<details>
<summary>Source code</summary>

```
(defn intersect?
  [loader-id]
  (get-in @state/OBSERVERS [loader-id :intersect?]))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [infinite-loader.api :refer [intersect?]]))

(infinite-loader.api/intersect? ...)
(intersect?                     ...)
```

</details>

---

### reload!

```
@param (keyword) loader-id
```

```
@usage
(reload! :my-loader)
```

<details>
<summary>Source code</summary>

```
(defn reload!
  [loader-id]
  (letfn [(enable-f  [] (enable!  loader-id))
          (disable-f [] (disable! loader-id))]
         (disable-f)
         (time/set-timeout! enable-f 50)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [infinite-loader.api :refer [reload!]]))

(infinite-loader.api/reload! ...)
(reload!                     ...)
```

</details>

---

### sensor

```
@param (keyword)(opt) loader-id
@param (map) loader-props
{:on-intersect (function)(opt)
 :on-leave (function)(opt)}
```

```
@usage
[sensor {:on-intersect (fn [] ...)
         :on-leave     (fn [] ...)}]
```

<details>
<summary>Source code</summary>

```
(defn sensor
  ([loader-props]
   [sensor (random/generate-keyword) loader-props])

  ([loader-id {:keys [on-intersect on-leave] :as loader-props}]
   (let [element-id (-> loader-id utils/loader-id->observer-id hiccup/value)
         callback-f (fn [%] (swap! state/OBSERVERS assoc-in [loader-id :intersect?] %)
                            (if % (if on-intersect (on-intersect))
                                  (if on-leave     (on-leave))))]
        (reagent/lifecycles {:component-did-mount    (fn [] (intersection-observer/setup-observer!  element-id callback-f))
                             :component-will-unmount (fn [] (intersection-observer/remove-observer! element-id)
                                                            (swap! state/OBSERVERS dissoc loader-id))
                             :reagent-render         (fn [] [infinite-loader loader-id])}))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [infinite-loader.api :refer [sensor]]))

(infinite-loader.api/sensor ...)
(sensor                     ...)
```

</details>

---

This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.

