(ns roman-numerals.core
  (:gen-class))

(def numerals {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})

(defn decimal-values [s]
  (map numerals s))

(defn roman [s]
  (reduce + (decimal-values s)))

(def first-numerals {1 "I"
               2 "II"
               3 "III"
               4 "IV"
               5 "V"
               6 "VI"
               7 "VII"
               8 "VIII"
               9 "IX"
               10 "X"
               20 "XX"
               30 "XXX"
               40 "XL"
               50 "L"
               60 "LX"
               70 "LXX"
               80 "LXXX"
               90 "XC"
               100 "C"})

(defn numeralize [x]
  "Takes a number and changes to the roman numeral"
  (numerals x 0))

(integer? (numeralize 19))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
