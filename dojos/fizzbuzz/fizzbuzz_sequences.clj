(ns dojofun.sequences
  (:gen-class))

(def fizzes
  (flatten (interleave (partition 2 (repeat nil)) (repeat "Fizz"))))

(def buzzes
  (flatten (interleave (partition 4 (repeat nil)) (repeat "Buzz"))))

(def fizzbuzzes
  (flatten (interleave (partition 14 (repeat nil)) (repeat "FizzBuzz"))))

(defn fizzbuzz []
  (map #(or %1 %2 %3 %4) fizzbuzzes fizzes buzzes (drop 1 (range))))

(println "First 100 FizzBuzz items are: \n" (take 100 (fizzbuzz)))
