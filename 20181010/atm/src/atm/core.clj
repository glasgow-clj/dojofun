(ns atm.core)

(defn deposit
  "Adds value into account structure"
  [account amount]
  (assoc account :transaction-log (conj (:transaction-log account) amount)))
