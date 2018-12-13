(ns traffic-lights.core-test
  (:require [clojure.test :refer :all]
            [traffic-lights.core :refer :all]))

(deftest test-initial-state
  (testing "Creates a new traffic light lit red with no amber or green"
    (is (= new-light {:red true :amber false :green false}))))

(deftest test-red-to-red-amber-cycle
   (testing "Cycles the lit state from red to red-amber"
     (is (= {:red true :amber true :green false} (state->lit (traffic-controller {:state :state/red :car-countdown 0}))))))