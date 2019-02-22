(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(deftest a-test
  (testing "restrict dolls with maximum weight"
    (let [expected-result #{{:name "hugh" :weight 5 :street-value 10}}]
      (is (= (smuggle #{{:name "hugh" :weight 5 :street-value 10}
                        {:name "maria" :weight 3 :street-value 10}} 6)
             expected-result)))))

(deftest b-test
  (let [expected-result #{{:name "matt" :weight 4 :street-value 15}}]
    (is (= (smuggle #{{:name "matt" :weight 4 :street-value 15}
                      {:name "dora" :weight 9 :street-value 12}}
                    5) expected-result))))

(deftest c-test
  (let [expected-result #{{:name "gary" :weight 3 :street-value 10}
                          {:name "dora" :weight 4 :street-value 8}}]
    (is (= (smuggle #{{:name "gary" :weight 3 :street-value 10}
                      {:name "dora" :weight 4 :street-value 8}}
                    8) expected-result))))
