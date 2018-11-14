(ns roman-numerals.core-test
  (:require [clojure.test :refer :all]
            [roman-numerals.core :refer :all]))

(deftest one-returns-char-I
  (testing "The number 1 returns I"
    (is (= (numeralize 1) "I"))))

(deftest two-returns-chars-II
  (testing "The number 2 returns II"
    (is (= (numeralize 2) "II"))))
