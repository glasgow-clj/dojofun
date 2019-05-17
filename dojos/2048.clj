(ns twentyfortyeight.core
  (:gen-class))


(defn make-board [size]
  (-> (vec (repeat size (vec (repeat size nil))))
      (insert-num 2)
      (insert-num 2)))

(def b4 (make-board 4))
(clojure.pprint/pprint b4 )


(defn choose-pos [] [(rand-int 4) (rand-int 4)])

(defn choose-empty-pos [board]
  (let [pos (choose-pos)]
    (if (get-in board pos)
      (recur board)
      pos)))

(defn insert-num [board num]
  (assoc-in board (choose-empty-pos board) num))

(defn munge [stack]
  (reduce (fn [new-stack square] (if (= (peek new-stack) square)
                                   (conj (pop new-stack) (* 2 square))
                                   (conj new-stack square))
            )
          []
          stack))

(munge (reverse [2 2 2 4]))



(defn move [board dir]
  (map munge board))

(-> (make-board 4)
    (move :right))
