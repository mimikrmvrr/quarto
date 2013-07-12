(ns quarto.states_and_moves
  (:use quarto.board))

(def max-moves (count all-pieces))

(def max-moves-from-one-player (/ max-moves 2))

(def state
  (atom 
    {:board (vec (repeat dim (vec (repeat dim nil))))
     :unused-pieces (set all-pieces)
     :current-piece nil}))


(def selected-piece (get @state :current-piece))

(defn select-piece
  [piece]
  (let [unused-pieces (get @state :unused-pieces)]
    (swap! state #(assoc % :current-piece piece))
    (swap! state #(assoc % :unused-pieces (disj (set unused-pieces) piece)))))
  ; [{:keys [unused-pieces current-piece] :as current-state} piece]
  ; (assoc current-state
    ; :unused-pieces (disj unused-pieces piece)
  ;   :current-piece piece))

(defn legal-move? [pos-x pos-y board]
  (nil? (get-in board [pos-x pos-y])))

(defn put-piece
  [piece pos-x pos-y]
  (let [board (get @state :board)]
    (when (legal-move? pos-x pos-y board)
      (swap! state #(assoc % :current-piece nil))
      (swap! state #(assoc % :board (assoc-in board [pos-x pos-y] piece))))))
  ; (assoc current-state
  ;   :board (assoc-in board [pos-x pos-y] piece)
  ;   :current-piece nil))

(defn win? []
  (let [board [get @state :board]]
    (some true?
      '((winning-combination? (rows board))
        (winning-combination? (columns board))
        (winning-combination? (diagonals board))))))

(defn all-filled? []
  (let [board (get @state :board)]
    (not-any? nil? (flatten board))))


(defn get-x [] nil)
(defn get-y [] nil)
(defn get-chosen-piece [] nil)


;;;????????whatttt
