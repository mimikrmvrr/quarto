(ns quarto.states_and_moves
  (require [quarto
            [board]]))


(defn choose-piece-for-other-player
  [{:keys [unused-pieces current-piece] :as current-state} piece]
  (assoc current-state
    :unused-pieces (disj unused-pieces piece)
    :current-piece piece))

; (defn make-move
;   [piece pos-x pos-y current-state]
  
;   )