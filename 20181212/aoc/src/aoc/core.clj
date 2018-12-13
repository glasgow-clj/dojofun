(ns aoc.core
  (:require [clojure.string :as string]
            [clojure.set :as set]
            [clojure.data :as data]
            [clojure.math.combinatorics :as math])
  (:gen-class))

(def input (as-> "frequencies.txt" $
                 (slurp $)
                 (string/split $ #"\n")
                 (map #(Long/parseLong %) $)))

(defn day1 [input]
  (apply + input))

(defn setfind [[total set] val]
  (let [newtotal (+ total val)]
    (if (set newtotal)
      (reduced newtotal)
      [newtotal (conj set newtotal)]))

  )


(defn day1-1 [input]
  (->> input
   (cycle)
   (reduce setfind [0 #{0}]))
  )


(day1 input)
(take 20 (day1-1 input))
(day1-1 input)

(def boxes (-> "checksum.txt"
               slurp
               (string/split #"\n"))
  )

(defn day2 [input]
  (->> input
       (map frequencies)
       (map set/map-invert)
       (map keys)
       (map set)
       (reduce (fn [acc v] (-> acc
                               (update 2 #(if (%2 2) (inc %1) %1) v)
                               (update 3 #(if (%2 3) (inc %1) %1) v)
                               )) {2 0, 3 0})
       (#(* (% 2) (% 3)))

       )
 )

(day2 boxes)

(->> (for [box1 boxes
           box2 boxes]
       (last (data/diff (vec box1) (vec box2))))
     (map (fn [x] [x (count (filter nil? x))]))
     (filter #(= 1 (second %)))
     ffirst
     (remove nil?)
     (apply str))

(data/diff (vec "heilo") (vec "hello"))
(math)
