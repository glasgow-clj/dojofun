(ns serpent.core
    (:require [reagent.core :as core]
              [clojure.string :as string]))

(enable-console-print!)

(println "This text is printed from src/hello-world/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(def render-map
    {:e \.
     :h \#})

(defn create-string-for-board
    [b]
    (string/join \newline (map (fn [line] (string/join (map render-map line)))
               b)))

(defn add-snakehead
    "add snake head in the middle"

    [[x y] b]
    (println [x y])
    (assoc-in b [x y] :h))

(defn empty-board
    "defines an empty snake board"
    [size]
    (vec (repeat size (vec (repeat size :e))))
    )

(def directions {:up [0 -1] :down [0 1] :left [-1 0] :right [1 0]})

(defn new-position [head direction]
    (mapv + head (get directions direction)))

(defn next-step [{:keys [snake direction board] :as stuff}]
    (println stuff) (let [new-snake (new-position (first snake) direction)
          new-board (add-snakehead new-snake board)]
         new-board))

(defonce app-state (atom {:text      "Kaa sszz-ays Hello Worldszszsz!"
                          :board     (empty-board 10)
                          :snake     [[5 5]]
                          :direction :up}))

(defn board []
    (println (:board @app-state))
    [:pre
     (create-string-for-board (:board @app-state))
     ])

(defn title []
    [:h1 (:text @app-state)])

(defn root []
    [:div
     [title]
     [board]])

(core/render root (.-body js/document))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

    (swap! app-state next-step)
)
(swap! app-state next-step)