(ns minesweeper.main)

(prn "Hello from Clojure Dojo")
(def expectations "Create a board, place mines,
select location, resolve selection")
(prn expectations)

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
  (vec (repeat size (vec (repeat size nil)))))

;variabled board
;expanding probing

(defn create-gamestate [size n-mines]
  {:private (create-board size n-mines) :public ()})
