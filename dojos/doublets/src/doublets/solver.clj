(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.tools.logging :as log]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn by-length [n coll]
  (filter #(= n (count %)) coll))

(defn start
  []
  )

(defn check-word []
  (let [w1 (rand-nth words)
        w2
        (rand-nth (filter #(= (count w1) (count %))
                          words))]
    [w1 w2]))



(defn one-letter-apart? [word1 word2]
  (= 1 (get (frequencies (map = word1 word2)) false)))

(defn one-step [word visited candidate-words]
  (filter #(one-letter-apart? word %)
          (remove (set visited) candidate-words)))

;;; word1 is starting point
;;; word2 has same length as word1
;;; restrict words to words of that length
;;; find all the words one letter away from word1

;;; take next search word
;;; call one step
;;; for each result if its the goal then we are done
;;;; if not, then push onto the search queue and keep going

(defn doublets
  [word1 word2]
  (let [useful-words (by-length (count word1) words)]
    (loop [search-queue [[word1 []]]]
      (let [[w v] (first search-queue)]
        #_(log/warnf "w=%s v=%s" w v)
        (cond
          (empty? search-queue) []
          (= word2 w) (conj v w)
          :else (recur (concat (map (fn [w2] [w2 (conj v w)]) (one-step w v useful-words)) (rest search-queue)))))
      )))