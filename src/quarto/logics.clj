(ns quarto.logics
  (:gen-class)
  (:use quarto.board 
        quarto.states_and_moves
        quarto.gui)
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
  (let [panel (doto (JPanel.)
                (.add message-label))
        button (doto (JButton. (ImageIcon. (io/resource "p0e.png")))
                 (.setBounds 520 135 50 75)
                 (.setBackground (Color. 241 221 196 255))
                 (.setBorderPainted false))
        frame (doto (JFrame. "Quarto Game")
                (.add panel BorderLayout/NORTH)
                (.add game-panel BorderLayout/CENTER)      
                (.pack)
                (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
                (.setVisible true)
                (.setSize (Dimension. 830 555)))]
    (if-not (nil? selected-piece)
      (.. frame getContentPane (add select-piece-button)))
    (for [piece-button (unused-pieces-buttons)]
      (.. frame getContentPane (add piece-button)))))

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