(ns roman)

(def symbols {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})

(defn deduct [acc v2]
  (let [v1 (last acc)]
    (cond
      (nil? v1) (conj acc v2)
      (< v1 v2) (conj (vec (take (dec (count acc)) acc)) (- v2 v1))
      :else (conj acc v2))))

(defn roman->arabic [roman]
  (str (apply + (reduce deduct [] (map symbols roman)))))