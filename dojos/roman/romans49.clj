(ns roman-numerals.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn number->numerals
  [number]
  (if (= number 9)
    (let [x (number->numerals 1)]
      (str x "X"))
    (if (= number 4)
      (let [x (number->numerals 1)]
        (str x "V"))
      (if (<= 5 number 8)
        (let [x (number->numerals (- number 5))]
          (str "V" x))
        (if (<= 10 number 49)
          (let [x (number->numerals (- number 10))]
            (str "X" x))
          (if (= number 1)
            "I"
            (if (= number 2)
              "II"
              (if (= number 3)
                "III"
                )
              )
            ))))))