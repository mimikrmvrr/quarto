(ns quarto.states_and_moves
  (:use quarto.board
        quarto.player
        quarto.gui))

(def max-moves (count all-pieces))

(def max-moves-from-one-player (/ max-moves 2))

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

(defn win?
  [{:keys [board]}]
  (some true?
    '((winning-combination? (rows board))
      (winning-combination? (columns board))
      (winning-combination? (diagonals board)))))

(defn all-filled?
  [{:keys [board]}]
  (not-any? nil? (flatten board)))


(defrecord Person [name]
  Player  
  (make-move [this {:keys [current-piece] :as current-state}]
    (if current-piece
      (let [state-after-move (put-piece current-state current-piece (get-x) (get-y))
            piece (get-chosen-piece state-after-move)]
            (choose-piece-for-other-player state-after-move piece))
      (let [piece (get-chosen-piece current-state)]
        (choose-piece-for-other-player current-state piece)))))

