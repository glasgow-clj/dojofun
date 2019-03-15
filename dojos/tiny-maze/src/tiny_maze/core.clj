(ns tiny-maze.core)
(def maze1 [[:S 0 1]
            [0  0 1]
            [1  0 :E]])

(defn make-move
  "takes a coordinate and a maze. returns a
  legal next coordinate
  or nil"
  [[x y] maze]
  (let [up [x (dec y)]
        down [x (inc y)]
        left [(dec x) y]
        right [(inc x) y]]

    (filter (comp #{0 :E}
                  (fn [p] (get-in maze p)))
            [up down left right])))

(defn solve-maze
  [maze]
  (loop [loc-mazes [{:loc [0 0] :maze maze}]]
    (when (not (empty? loc-mazes))
      (let [{:keys [loc maze]} (first loc-mazes)
            next-locs (make-move loc maze)
            next-states (map (fn [loc] {:loc loc
                                        :maze
                                             (update-in maze loc
                                                        {0 :x :E :E})})
                             next-locs)]
        (if (= (get-in maze loc) :E)
          maze
          (recur (concat next-states (rest loc-mazes))))))))



