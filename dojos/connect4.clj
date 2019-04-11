(ns connect4)

(def empty-board
  (vec (repeat 7 [])))

(defn make-move "check column full?; make move;"
  [current-board playerID columnNum]
  (when (< (count (get columnNum current-board)) 6)
    (update current-board columnNum conj playerID)))

(defn vector-win? [col]
  (some #(when (apply = %) (first %)) (partition 4 1 col)))

(defn pad-board [n board]
  (mapv
    (fn [c] (into c (repeat (- n (count c)) nil)))
    board))

(defn transpose [board]
  (apply mapv vector board))

(defn generate-coordinates
  [x y]
  (take 4 (iterate (fn [[x y]] [(inc x) (inc y)]) [x y])))

(defn translate-coordinates
  [max-x max-y])

(defn diagonal-win? [board]

  )




(defn won? [board]
  (or
    (some vector-win? board)
    (some vector-win? (transpose (pad-board 6 board)))
    (diagonal-win? board)))
