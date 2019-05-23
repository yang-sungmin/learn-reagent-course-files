(ns giggin.components.gig-editor)

(defn gig-editor
  [modal values]
  [:div.modal (when @modal {:class "active"})
   [:div.modal__overlay]
   [:div.modal__container
    [:div.modal__body
     [:div.form__group
      [:label.form__label {:for "title"} "title"]
      [:input.form__input {:type "text"
                           :id "title"
                           :value (:title @values)
                           :on-change #(swap! values assoc :title (.. % -target -value))}]]]
    [:div.modal__footer
     [:button.btn.btn--link.float--left
      {:on-click #(reset! modal false)}
      "Cancel"]
     [:button.btn.btn--secondary
      "Save"]]]])
