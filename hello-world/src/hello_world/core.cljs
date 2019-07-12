(ns hello-world.core
  (:require [reagent.core :as rcore]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello ha ha world!"}))



(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(def board [[true false false]
            [false true false]
            [false true false]
            [true false false]
            [false false true]])

(defn probe-location [board [x y]]
  (get-in board [x, y]))

(defn create-list-of-coords [[x y]]
  [[(dec x) (dec y)]
   [(dec x) y]
   [x (dec y)]
   [(inc x) (inc y)]
   [(inc x) y]
   [x (inc y)]
   [(dec x) (inc y)]
   [(inc x) (dec y)]])

(defn count-truth
  "return the number of truthy values"
  [s] (count (filter identity s)))

(defn count-mines [board [x y]]
  (count-truth (map #(probe-location board %) (create-list-of-coords [x y]))))

(defn sweep-location [board coords]
  (if-not (probe-location board coords)
    (count-mines board coords)
    :BANG))

(defn create-board [size n-mines]
  (mapv vec (partition size
              (shuffle (concat (repeat n-mines true)
                               (repeat (- (* size size)
                                          n-mines) false)))))
  )

(defn create-nil-board [size]
  (vec (repeat size (vec (repeat size "x")))))

;variabled board
;expanding probing

(defn create-gamestate [size n-mines]
  {:private (create-board size n-mines) :public (create-nil-board size)})

(def gamestate
  (rcore/atom (create-gamestate 6 6)))

(defn title []
  [:h1 "Welcome to Minesweeper (Under Construction)"])

(defn on-clicked
  [row-idx col-idx]
  (println "Clicked!" row-idx col-idx)
  (swap! gamestate #(assoc-in % [:public row-idx col-idx] (sweep-location (:private %) [row-idx col-idx]))))



(defn board-size [{:keys [private]}]
  (count private))

(defn table []
  (let [size (board-size @gamestate)]
    (into [:table](for [row-idx (range size)]
                    [:tr (for [col-idx (range size)]
                           [:td {:on-click #(on-clicked row-idx col-idx)} (get-in @gamestate [:public row-idx col-idx])])]))))

(defn some-component []
  [:div
   [title]
   [table]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red"]
    ]])



(rcore/render [some-component title]
              (.-body js/document))
