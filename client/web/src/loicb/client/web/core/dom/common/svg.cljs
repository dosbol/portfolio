(ns loicb.client.web.core.dom.common.svg
  (:require [loicb.client.web.core.dom.common.svg.nav-diamond :as diamond]
            [loicb.client.web.core.dom.common.svg.nav-arrow :as arrow]
            [loicb.client.web.core.dom.common.svg.nav-menu :as menu]
            [re-frame.core :as rf]))

;; theme

(def moon-icon
  [:svg.header-logo
   {:viewBox "0 0 20 20"}
   [:path
    {:d "M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"}]])

(def sun-icon
  [:svg.header-logo
   {:viewBox "0 0 20 20"}
   [:path
    {:d "M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"}]])

(defn theme-logo
  []
  [:div.pointer
   {:on-click #(rf/dispatch [:evt.app/toggle-theme])}
   (if (= :dark @(rf/subscribe [:subs/pattern '{:app/theme ?x}]))
     sun-icon
     moon-icon)])

;; editor/reader mode

(def pen-on-paper-icon
  [:svg.header-logo
   {:viewBox "0 0 330 330"}
   [:path
    {:d "M75 180v60c0 8.284 6.716 15 15 15h60a15 15 0 0 0 10.606-4.394l164.999-165c5.858-5.858 5.858-15.355 0-21.213l-60-60a14.997 14.997 0 0 0-21.211.001l-165 165A14.994 14.994 0 0 0 75 180zm30 6.213 150-150L293.787 75l-150 150H105v-38.787z"}]
   [:path
    {:d "M315 150.001c-8.284 0-15 6.716-15 15V300H30V30h135c8.284 0 15-6.716 15-15s-6.716-15-15-15H15C6.716 0 0 6.716 0 15v300c0 8.284 6.716 15 15 15h300c8.284 0 15-6.716 15-15V165.001c0-8.285-6.716-15-15-15z"}]])

(def eye-icon
  [:svg.header-logo
   {:viewBox "0 0 489.935 489.935"}
   [:path
    {:d "M486.617 255.067c4.6-6.3 4.4-14.9-.5-21-74.1-91.1-154.1-137.3-237.9-137.3-142.1 0-240.8 132.4-244.9 138-4.6 6.3-4.4 14.9.5 21 74 91.2 154 137.4 237.8 137.4 142.1 0 240.8-132.4 245-138.1zm-245 103.8c-69.8 0-137.8-38.4-202.4-114 25.3-29.9 105.7-113.8 209-113.8 69.8 0 137.8 38.4 202.4 114-25.3 29.9-105.7 113.8-209 113.8z"}]
   [:path
    {:d "M244.917 157.867c-48 0-87.1 39.1-87.1 87.1s39.1 87.1 87.1 87.1 87.1-39.1 87.1-87.1-39.1-87.1-87.1-87.1zm0 139.9c-29.1 0-52.8-23.7-52.8-52.8s23.7-52.8 52.8-52.8 52.8 23.7 52.8 52.8-23.7 52.8-52.8 52.8z"}]])

(defn user-mode-logo
  []
  [:div.pointer
   {:on-click #(rf/dispatch [:evt.user/toggle-mode])}
   (if (= :editor @(rf/subscribe [:subs/pattern '{:user/mode ?mode}]))
     eye-icon
     pen-on-paper-icon)])

;; mobile nav

(def burger-icon
  [:svg.burger
   {:viewBox "0 0 20 20"}
   [:path
    {:clip-rule "evenodd"
     :d
     "M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
     :fill-rule "evenodd"}]])

;; post header

(def eye-icon-post
  [:svg.post-header-icon
   {:viewBox "0 0 489.935 489.935"}
   [:path
    {:d "M486.617 255.067c4.6-6.3 4.4-14.9-.5-21-74.1-91.1-154.1-137.3-237.9-137.3-142.1 0-240.8 132.4-244.9 138-4.6 6.3-4.4 14.9.5 21 74 91.2 154 137.4 237.8 137.4 142.1 0 240.8-132.4 245-138.1zm-245 103.8c-69.8 0-137.8-38.4-202.4-114 25.3-29.9 105.7-113.8 209-113.8 69.8 0 137.8 38.4 202.4 114-25.3 29.9-105.7 113.8-209 113.8z"}]
   [:path
    {:d "M244.917 157.867c-48 0-87.1 39.1-87.1 87.1s39.1 87.1 87.1 87.1 87.1-39.1 87.1-87.1-39.1-87.1-87.1-87.1zm0 139.9c-29.1 0-52.8-23.7-52.8-52.8s23.7-52.8 52.8-52.8 52.8 23.7 52.8 52.8-23.7 52.8-52.8 52.8z"}]])

(def done-icon
  [:svg.post-header-icon.done
   {:viewBox "0 0 48 48"}
   [:path
    {:d "M40.6 12.1L17 35.7 7.4 26.1 4.6 29 17 41.3 43.4 14.9z"}]])

(def close-icon
  [:svg.post-header-icon.close
   {:viewBox "0 0 48 48"}
   [:path
    {:d "M21.5 4.5H26.501V43.5H21.5z",
     :transform "rotate(45.001 24 24)"}]
   [:path
    {:d "M21.5 4.5H26.5V43.501H21.5z",
     :transform "rotate(135.008 24 24)"}]])

(def trash-icon
  [:svg.post-header-icon.trash
   {:viewBox "0 0 50 50"}
   [:path
    {:d
     "M 21 2 C 19.354545 2 18 3.3545455 18 5 L 18 7 L 10.154297 7 A 1.0001 1.0001 0 0 0 9.984375 6.9863281 A 1.0001 1.0001 0 0 0 9.8398438 7 L 8 7 A 1.0001 1.0001 0 1 0 8 9 L 9 9 L 9 45 C 9 46.645455 10.354545 48 12 48 L 38 48 C 39.645455 48 41 46.645455 41 45 L 41 9 L 42 9 A 1.0001 1.0001 0 1 0 42 7 L 40.167969 7 A 1.0001 1.0001 0 0 0 39.841797 7 L 32 7 L 32 5 C 32 3.3545455 30.645455 2 29 2 L 21 2 z M 21 4 L 29 4 C 29.554545 4 30 4.4454545 30 5 L 30 7 L 20 7 L 20 5 C 20 4.4454545 20.445455 4 21 4 z M 11 9 L 18.832031 9 A 1.0001 1.0001 0 0 0 19.158203 9 L 30.832031 9 A 1.0001 1.0001 0 0 0 31.158203 9 L 39 9 L 39 45 C 39 45.554545 38.554545 46 38 46 L 12 46 C 11.445455 46 11 45.554545 11 45 L 11 9 z M 18.984375 13.986328 A 1.0001 1.0001 0 0 0 18 15 L 18 40 A 1.0001 1.0001 0 1 0 20 40 L 20 15 A 1.0001 1.0001 0 0 0 18.984375 13.986328 z M 24.984375 13.986328 A 1.0001 1.0001 0 0 0 24 15 L 24 40 A 1.0001 1.0001 0 1 0 26 40 L 26 15 A 1.0001 1.0001 0 0 0 24.984375 13.986328 z M 30.984375 13.986328 A 1.0001 1.0001 0 0 0 30 15 L 30 40 A 1.0001 1.0001 0 1 0 32 40 L 32 15 A 1.0001 1.0001 0 0 0 30.984375 13.986328 z"}]])

(def pen-on-paper-post-icon
  [:svg.post-header-icon
   {:viewBox "0 0 330 330"}
   [:path
    {:d "M75 180v60c0 8.284 6.716 15 15 15h60a15 15 0 0 0 10.606-4.394l164.999-165c5.858-5.858 5.858-15.355 0-21.213l-60-60a14.997 14.997 0 0 0-21.211.001l-165 165A14.994 14.994 0 0 0 75 180zm30 6.213 150-150L293.787 75l-150 150H105v-38.787z"}]
   [:path
    {:d "M315 150.001c-8.284 0-15 6.716-15 15V300H30V30h135c8.284 0 15-6.716 15-15s-6.716-15-15-15H15C6.716 0 0 6.716 0 15v300c0 8.284 6.716 15 15 15h300c8.284 0 15-6.716 15-15V165.001c0-8.285-6.716-15-15-15z"}]])

(def plus-icon
  [:svg.post-header-icon.plus
   {:viewBox "0 0 24 24"}
   [:path {:d "M24 10h-10v-10h-4v10h-10v4h10v10h4v-10h10z"}]])

;; post authors

(def clock-icon
  [:svg.post-icon
   {:viewBox "0 0 32 32" :fill "none"}
   [:circle {:cx "16" :cy "16" :r "13" :stroke "#535358" :stroke-width "2"}]
   [:path {:stroke "#535358" :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M16 8v8l4 4"}]])

(def pen-icon
  [:svg.post-icon
   {:stroke "#535358" :fill "none" :stroke-linejoin "miter" :viewBox "0 0 24 24" :xmlns "http://www.w3.org/2000/svg" :stroke-linecap "round" :stroke-width "1"}
   [:polygon {:points "16 3 20 7 6 21 2 21 2 17 16 3" :fill "#059cf7" :opacity "0.1" :stroke-width "0"}]
   [:polygon {:points "16 3 20 7 6 21 2 21 2 17 16 3"}]
   [:line {:x1 "12" :y1 "21" :x2 "22" :y2 "21"}]])

;; navigation

(def right-arrow arrow/item)
(def diamond diamond/item)
(def menu menu/item)