(ns quarto.states_and_moves
  (require [quarto
            [board]]))


(defn choose-piece-for-other-player
  [{:keys [unused-pieces current-piece] :as current-state} piece]
  (assoc current-state
    :unused-pieces (disj unused-pieces piece)
    :current-piece piece))

(defn legal-move? [pos-x pos-y board]
  (nil? (get-in board [pos-x pos-y])))

(defn put-piece
  [{:keys [board current-piece] :as current-state} piece pos-x pos-y]
  (assoc current-state
    :board (assoc-in board [pos-x pos-y] piece)
    :current-piece nil))

