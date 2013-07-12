(ns quarto.logics
  (:gen-class)
  (:use quarto.gui)
        ; quarto.player)
  (:import
    (java.awt Color Dimension Image BorderLayout)
    (javax.swing JPanel JFrame ImageIcon JButton JLabel JTextField JOptionPane Icon)
    (java.awt.event ActionListener MouseListener MouseMotionListener)
    javax.imageio.ImageIO
    java.io.File
    java.lang.Math)
  (:require [clojure.java.io :as io]))


; (defrecord Person [name]
;   Player  
;   (make-move [this]
;     (if-let [piece (get @state :current-piece)]
;       (put-piece piece (get-x) (get-y))
;       (select-piece (get-chosen-piece)))))

(defn start-game []
	(window))

(defn end 
  [win? winner]
  (if win? 
    (.setText message-label (str (name winner) " won!")
    (.settext message-label "Game over without winner!"))))

; (defn play-game []
;   (loop [current-player :player1
;          next-turn :player2]
;     (cond
;       (win?) (end true next-turn)
;       (all-filled?) (end false :none)
;       :else 
;       (do
;       	(make-move)
;       	(recur next-turn current-player)))))