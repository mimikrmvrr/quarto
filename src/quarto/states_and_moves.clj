(ns quarto.states_and_moves
  (:use quarto.board)
  (:import (javax.swing JOptionPane)))

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

(defn legal-move? [pos-x pos-y board]
  (nil? (get-in board [pos-x pos-y])))

(defn put-piece
  [piece pos-x pos-y]
  (let [board (get @state :board)]
    (when (legal-move? pos-x pos-y board)
      (swap! state #(assoc % :current-piece nil))
      (swap! state #(assoc % :board (assoc-in board [pos-x pos-y] piece))))))

(defn win? []
  (let [board [get @state :board]]
    (some true?
      '((winning-combination? (rows board))
        (winning-combination? (columns board))
        (winning-combination? (diagonals board))))))

(defn all-filled? []
  (let [board (get @state :board)]
    (not-any? nil? (flatten board))))

(defn end? []
  (cond 
    (win?) (JOptionPane/showMessageDialog  nil, "Win! Start a new game?")
    (all-filled?) (JOptionPane/showMessageDialog  nil, "Game over without winner!won! Start a new game?")))
