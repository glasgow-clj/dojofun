(ns numbers-to-lcd.core)

(defn- display
  [lcd-number]
  (doseq [lcd-component lcd-number]
    (println lcd-component)))


(def ^:private numbers->lcd {:0 [" __ "
                                 "|  |"
                                 "|__|"]
                             :1 [""
                                 "   |"
                                 "   |"]
                             :2 [" __ "
                                 " __|"
                                 "|__ "]
                             :3[" __ "
                                " __|"
                                " __|"]
                             :4 [""
                                 "|__|"
                                 "   |"]
                             :5 [" __ "
                                 "|__ "
                                 " __|"]
                             :6 [" __ "
                                 "|__ "
                                 "|__|"]
                             :7 [" __"
                                 "   |"
                                 "   |"]
                             :8 [" __ "
                                 "|__|"
                                 "|__|"]
                             :9[" __ "
                                "|__|"
                                "   |"]})

(defn- display-numbers [& numbers]
  (doall (map (comp display numbers->lcd) numbers)))

(defn display-horizontally [& numbers]
  (reduce
    (fn [acc item]
      )
    [[] [] []]
    (map numbers->lcd numbers))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
