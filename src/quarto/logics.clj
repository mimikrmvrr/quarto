(ns quarto.logic
  (:gen-class)
  (:use quarto.board 
        quarto.states_and_moves
        quarto.gui
        quarto.player))


(defrecord Person [name]
  Player  
  (make-move [this]
    (if-let [piece (get @state :current-piece)]
      (put-piece piece (get-x) (get-y))
      (select-piece (get-chosen-piece)))))

(defn play-game []
  (loop [current-player :player1
         next-turn :player2]
    (cond
      (win?) (end true next-turn)
      (all-filled?) (end false :none)
      :else 
      (do
      	(make-move)
      	(recur next-turn current-player)))))