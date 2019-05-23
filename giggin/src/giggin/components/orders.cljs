(ns giggin.components.orders
      (:require [giggin.state :as state]))

(defn total
  []
  (->> @state/orders
       (map (fn [[id quant]] (* quant (get-in @state/gigs [id :price]))))
       (reduce +)))

(defn orders
  []
  [:aside
   [:div.order
    [:div.body
     (for [[id quant] @state/orders]
       [:div.item {:key id}
        [:div.img
         [:img {:src (get-in @state/gigs [id :img])
                :alt (get-in @state/gigs [id :title])}]]
        [:div.content
         [:p.title (str (get-in @state/gigs [id :title]) " \u00D7 " quant)]]
        [:div.action
         [:div.price (* (get-in @state/gigs [id :price]) quant)]
         [:button.btn.btn--link.tooltip
          {:data-tooltip "Remove"
           :on-click (fn [] (swap! state/orders dissoc id))}
          [:i.icon.icon--cross]]]])]
    [:div.total
     [:hr]
     [:div.item
      [:div.content "Total"]
      [:div.action
       [:div.price (total)]]
      [:button.btn.btn--link.tooltip
       {:data-tooltip "Remove all"
        :on-click (fn [] (reset! state/orders {}))}
       [:i.icon.icon--delete]]]]]])