(ns giggin.components.gigs
  (:require [reagent.core :as r]
            [giggin.state :as state]
            [giggin.helpers :refer [format-price]]
            [giggin.components.gig-editor :refer [gig-editor]]))

(defn gigs
  []
  (let [modal (r/atom false)
        values (r/atom {:id nil :title "" :desc "" :price 0 :sold-out false})
        add-to-order #(swap! state/orders update % inc)]
       (fn
         []
         [:main
          [:div.gigs
           [:button.add-gig
            {:on-click #(reset! modal true)}
            [:div.add_title
             [:i.icon.icon--plus]
             [:p "Add gig"]]]
           [gig-editor modal values]
           (for [{:keys [id img title price desc]} (vals @state/gigs)]
                [:div.gig {:key id}
                 [:img.gig__artwork {:src img :alt title}]
                 [:div.gig__body
                  [:div.gig__title
                   [:div.btn.btn--primary.float--right.tooltip
                    {:data-tooltip "Add to order"
                     :on-click #(add-to-order id)}
                    [:i.icon.icon--plus]] title]
                  [:p.gig__price (format-price price)]
                  [:p.gig__desc desc]]])]])))
