(ns quarto.player)

(defprotocol Player
  (make-move [this state]))
