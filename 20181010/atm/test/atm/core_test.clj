(ns atm.core-test
  (:require [clojure.test :refer :all]
            [atm.core :as atm]))

(deftest deposit-into-account

  (let [account {:transaction-log []}])

  (testing "A single deposit made into an account increases the number in the transaction log by 1"
    (let [result (atm/deposit account 1)]
      (is (= 1 (count (:transaction-log result))))))

  (testing "Multiple deposites into an account are recorded"
    (let [acc1 (atm/deposit account 1)
          acc2 (atm/deposit acc1 7)]
      (is (= 2 (count (:transaction-log acc2)))))))
