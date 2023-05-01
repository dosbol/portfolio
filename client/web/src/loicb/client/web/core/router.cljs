(ns loicb.client.web.core.router
  (:require [loicb.client.web.core.dom.page :refer [page-type-1 page-type-2]] 
            [goog.object :as gobj]
            [reitit.frontend :as rei]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend.history :as rfh]
            [re-frame.core :as rf]))

(def routes
  [["/"
    {:name :home
     :db-page-name :home
     :post-route :portfolio/post
     :title "Portfolio"
     :view page-type-1}]
   
   ["/portfolio/:post-id"
    {:name :portfolio/post
     :db-page-name :home
     :title "Portfolio"
     :view  page-type-1
     :controllers [{:parameters {:path [:post-id]}
                    :start (fn [parameters]
                             (rf/dispatch [:evt.nav/close-navbar :main])
                             (rf/dispatch [:evt.page/set-active-post
                                           (-> parameters :path :post-id uuid)]))
                    :stop  (fn [_]
                             (rf/dispatch [:evt.page/clear-active-post]))}]}]

   ["/blog"
    [""
     {:name :blog
      :db-page-name :blog
      :post-route :blog/post
      :title "Blog"
      :view page-type-1}]
    
    ["/:post-id"
     {:name :blog/post
      :db-page-name :blog
      :title "Blog"
      :view  page-type-1
      :controllers [{:parameters {:path [:post-id]}
                     :start (fn [parameters]
                              (rf/dispatch [:evt.nav/close-navbar :main])
                              (rf/dispatch [:evt.page/set-active-post
                                            (-> parameters :path :post-id uuid)]))
                     :stop  (fn [_]
                              (rf/dispatch [:evt.page/clear-active-post]))}]}]]
   
   ["/about"
    {:name :about
     :db-page-name :about
     :title "About me"
     :view page-type-2}]

   ["#footer-contact"
    {:name :contact}]])

(def router
  (rei/router routes))

(defn on-navigate
  "Implementation provided by reitit to enable controllers."
  [new-match]
  (when new-match
    (rf/dispatch [:evt.page/set-current-view new-match])))

(defn ignore-anchor-click?
  "Function provided by reitit doc to ignore reitit routing on anchor link."
  [router e el uri]
  ;; Add additional check on top of the default checks
  (and (rfh/ignore-anchor-click? router e el uri)
       (not= "false" (gobj/get (.-dataset el) "reititHandleClick"))))

(defn init-routes! []
  (rfe/start!
   router
   on-navigate
   {:use-fragment true
    :ignore-anchor-click? ignore-anchor-click?}))