(ns traffic-lights.core
  (:gen-class))

(def new-light {:red true :amber false :green false})

(def state->lit {:state/red {:red true :amber false :green false}
                   :state/amber {:red false :amber true :green false}
                   :state/red-amber {:red true :amber true :green false}
                   :state/green {:red false :amber false :green true}
                   :state/standby {:red true :amber false :green false}})

(defn traffic-controller
  [{:keys [state car-countdown state-ts] :as args}]
  (condp = state
    :state/red (if (pos? car-countdown)
                 args
                 {:state :state/red-amber :state-ts (System/currentTimeMillis)})
    :state/red-amber (let [ms-ago (- (System/currentTimeMillis) state-ts)]
                       (if (> 5000 ms-ago)
                         {:state :state/green}
                         args))
    :state/green nil
    :state/amber nil
    :state/standby nil
    nil
    ))

