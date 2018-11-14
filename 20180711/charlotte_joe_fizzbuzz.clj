 (ns dojofun.core
  (:gen-class))

  (defn- fizzbuzz [n]
    (cond
      (zero? n) (str n)
      (and (= (mod n 3) 0) (= (mod n 5) 0)) "fizzbuzz"
      (= (mod n 3) 0)  "fizz"
      (= (mod n 5) 0) "buzz"
      :else (str n)
    ))

(defn buzzer []
    (map fizzbuzz (range 101))
    )
