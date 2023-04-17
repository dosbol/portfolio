(ns loicb.client.web.core.dom.page
  (:require [loicb.client.web.core.dom.hiccup :as h]
            [loicb.client.web.core.dom.page.post :refer [page-post]]
            [loicb.client.web.core.dom.common.svg :as svg]
            [re-frame.core :as rf]))

(defn page
  "Given the `page-name`, returns the page content."
  [page-name]
  (let [all-posts       (->> @(rf/subscribe [:subs.post/posts page-name])
                             (map #(assoc % :post/hiccup-content (h/md->hiccup (:post/md-content %)))))
        new-post        {:post/id "new-post-temp-id" :post/title "New Post"}
        posts           (if @(rf/subscribe [:subs/pattern '{:app/user ?x}])
                          (conj all-posts new-post)
                          all-posts)
        active-post-id  @(rf/subscribe [:subs/pattern '{:page/active-post ?x}])
        _              (when-not active-post-id
                         (rf/dispatch [:evt.page/set-active-post (:post/id (first posts))]))
        active-post     (->> posts
                             (filter #(= active-post-id (:post/id %)))
                             first)
        left-menu-open? @(rf/subscribe [:subs/pattern '{:nav.left-menu/open? ?x}])
        page-title      @(rf/subscribe [:subs/pattern '{:app/current-view {:data {:title ?x}}}])]
    [:section.container
     {:id  (name page-name)
      :key (name page-name)}
     (if left-menu-open?
       [:div.left
        [:div.menu-title
         [:button.burger-btn
          {:on-click #(rf/dispatch [:evt.nav/toggle :left-menu])}
          svg/burger-icon]
         [:h1 page-title]]
        [:ul
         (doall
          (for [post posts
                :let [{:post/keys [id title]} post]]
            [:li {:key title}
             [:a
              {:key title
               :on-click #(rf/dispatch [:evt.page/set-active-post id])}
              [:div
               (when (= active-post-id id) {:class "active"})
               [svg/right-arrow]
               [:h2 title]]]]))]]
       [:div.left-close
        [:div.menu-title
         [:button.burger-btn
          {:on-click #(rf/dispatch [:evt.nav/toggle :left-menu])}
          svg/burger-icon]
         [:h1 page-title]]])
     [:div.right
      (page-post page-name active-post)]]))