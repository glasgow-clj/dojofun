(ns hanjie.core)

(defn onestep
  [ll]
  (map #(update ll % inc) (range (count ll))))


(defn one-step-loop [ll-seq]
  (into #{} (mapcat onestep ll-seq)))


(defn multi-step-loop [blank-spaces locns]
  (loop [count blank-spaces
         ll-seq (list locns)]

    (if (zero? count) ll-seq
                      (recur (dec count) (one-step-loop ll-seq)))
    )
  )

(defn build-line [blocks-vec blanks-vec]
  (flatten (interleave (map #(repeat % :off) blanks-vec)
                       (map #(repeat % :on) blocks-vec))))

(defn prefer
  [spaces locs]
  (cond (= 0 spaces) nil
        (= 0 locs) nil
        )
  )
