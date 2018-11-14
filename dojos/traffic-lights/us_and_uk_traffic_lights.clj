(ns traffic-lights.core
  (:gen-class))

(def us-light-sequence [:green :yellow :red])

(def uk-light-sequence [:green :amber :red #{:red :amber}])

(defn update-light [light-sequence current-light]
  {:pre [((set light-sequence) current-light)]}
  (let [infinite-sequence (cycle light-sequence)]
    (second (drop-while #(not= current-light %) infinite-sequence))))

(def update-light-us (partial update-light us-light-sequence))

(def update-light-uk (partial update-light uk-light-sequence))

(defn verify [sequence update-fn]
  (->> (cycle sequence)
       (drop (dec (count sequence)))
       (take (count sequence))
       (map update-fn)
       (= sequence)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def intersection {:north-south :green
                   :east-west :red})

(defn should-step? [intersection]
  (reduce (fn [acc [key val]] (if (not= val :red)
                                (conj acc key))) [] intersection))

(defn step [update-fn intersection]
  (cond
    )
  (-> intersection
      (update :north-south update-fn)
      (update :east-west update-fn)))