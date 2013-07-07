(ns quarto.board)


(defrecord Piece[shape size color holed])

(def all-pieces
  (for [shape [:square :circle]
        size  [:tall :short]
        color [:black :white]        
        holed [:hollow :solid]]
    (Piece. shape size color holed)))

(def dim 4)

(def start-state
  {:board (vec (repeat dim (vec (repeat dim nil))))
   :unused-pieces (set all-pieces)
   :current-piece nil})