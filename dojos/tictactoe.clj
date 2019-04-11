(ns test.core
  (:gen-class))

(def blank-grid [[:_ :_ :_]
                 [:_ :_ :_]
                 [:_ :_ :_]])

(def next-turn {:x :0 :0 :x})

(def new-game {:grid blank-grid
               :turn :0})

(defn winner [grid]
  (map set grid))

(defn move [{:keys [grid turn] :as game} coordinates]
  (if (= :_ (get-in grid coordinates))
    {:grid (update-in grid coordinates (constantly turn))
     :turn (next-turn turn)}
    game))

(-> new-game
    (move [1 2])
    (move [0 2])
    (move [1 1])
    (move [2 2])
    (move [2 2]))
