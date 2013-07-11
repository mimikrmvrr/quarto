(ns quarto.core
  (:gen-class)
  (:use quarto.board 
        quarto.states_and_moves
        quarto.gui))

; (def dim 4)

; (defrecord Piece[shape size color holed])

; (def all-pieces
;   (for [shape [:square :circle]
;         size  [:tall :short]
;         color [:black :white]        
;         holed [:hollow :solid]]
;     (Piece. shape size color holed)))

; (def start-state
;   "The board state in the begining of the game."
;   {:board (vec (repeat dim (vec (repeat dim nil))))
;    :unused-pieces (set all-pieces)
;    :current-piece nil})

; (defn choose-piece-for-other-player
;   [{:keys [unused-pieces current-piece] :as current-state} piece]
;   (assoc current-state
;     :unused-pieces (disj unused-pieces piece)
;     :current-piece piece))

; (defn make-move
;   [piece pos-x pos-y current-state]
  
;   )

(defn play
  [player1 player2]
    (loop [current-player player1
           next-turn player2
           state start-state]
      ; (if (= state start-state)
      ;   )
      (cond
        (win? state) player2
        (all-filled? state) :tie
        ; :else (recur r (action p state))
        )))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do
    (names-window)))
    ; (run)))
