(ns super-enhanced-dollz)

(defn ratio [m]
  (assoc m :ratio (/ (:value m) (:weight m))))

(defn super-dollz [ed]
  (reverse (sort-by :ratio ed)))

(def enhanced-dollz
  (map ratio (map (partial zipmap [:name :weight :value])
               [[" luke" 9 150]
                [" anthony" 13 35]
                [" candice" 153 200]
                [" dorothy" 50 160]
                [" puppy" 15 60]
                [" thomas" 68 45]
                [" randal" 27 60]
                [" april" 39 40]
                [" nancy" 23 30]
                [" bonnie" 52 10]
                [" marc" 11 70]
                [" kate" 32 30]
                [" tbone" 24 15]
                [" tommy" 48 10]
                [" uma" 73 40]
                [" grumpkin" 42 70]
                [" dusty" 43 75]
                [" grumpy" 22 80]
                [" eddie" 7 20]
                [" tory" 18 12]
                [" sally" 4 50]
                [" babe" 30 10]])))

(defn smuggle []
  (reduce (fn [acc v] (if (< (:weight v) (:remaining-weight acc))
                        {:remaining-weight (- (:remaining-weight acc) (:weight v))
                         :bag-dollz (conj (:bag-dollz acc) v)}
                        acc))
    {:remaining-weight 400
     :bag-dollz '()}
    (super-dollz enhanced-dollz)))
