(ns doll-smuggler.core)

(defn dolls-with-drugs-into-old-woman [old-woman-max-weight acc current-doll]
  (let [total-weight (apply + (map :weight acc))]
    (if (< (+ total-weight (:weight current-doll)) old-woman-max-weight)
      (conj acc current-doll)
      acc))
  )

(defn smuggle
  "smuggle drugs using old woman and dolls"
  [dolls old-woman-max-weight]
       (let [dolls-sorted-by-weight (reverse (sort-by :weight dolls))]
         (reduce
           (partial dolls-with-drugs-into-old-woman old-woman-max-weight)
           #{}
           dolls-sorted-by-weight)))
