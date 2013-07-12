(ns quarto.gui
  (:use quarto.board
        quarto.states_and_moves)
  (:import
    (java.awt Color Dimension Image BorderLayout)
    (javax.swing JPanel JFrame ImageIcon JButton JLabel JTextField JOptionPane Icon)
    (java.awt.event ActionListener MouseListener MouseMotionListener)
    javax.imageio.ImageIO
    java.io.File
    java.lang.Math)
  (:require [clojure.java.io :as io]))

(def img-url (io/resource "gameboard.png"))

(defn piece-url 
  [piece]
  (io/resource (str "p" (.id piece) "e.png")))

(def selected-piece (get @state :current-piece))

(def image-background (.getImage (ImageIcon. img-url)))

(def game-panel
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))))

; (defn run [] 
;   (doto (JFrame. "Quarto Game")
;     (.setContentPane game-panel)
;     (.setSize (Dimension. 800 600))
;     (.setVisible true)))

(defn coord-x [id]
  (+ (* (mod id 4) 60) 520))

(defn coord-y [id]
  (+ 150 (* (Math/round (Math/floor (/ id 4))) 75)))


(defn select-piece-button [piece]
  (doto (JButton. (ImageIcon. (piece-url piece)))
    (.setBounds 640 60 50 75)
    (.setBackground (Color. 241 221 196 255))
    (.setBorderPainted false)
    (.setName (str (.id piece)))))

(defn move-to-selected [event-source]
  (let [id (Integer. (.getName event-source))
        unused (get @state :unused-pieces)
        coincidence-coll (filter #(= id (.id %)) unused)
        moved-piece (get coincidence-coll 0)]
    (do ;(prn (str (.id moved-piece)))
        (select-piece moved-piece)
       ; (start-game)
        (prn "ok"))))

(def choose-piece 
  (proxy [ActionListener] []
    (actionPerformed [event] (do (prn "action!")
                                 (if (nil? selected-piece)
                                    (do (prn "select")
                                        (move-to-selected (.getSource event))))))))

(def message-label 
  (doto (JLabel.)
    (.setText "Some Text")))

(defn place-piece
  [piece]
  (doto (JButton. (ImageIcon. (piece-url piece)))
    (.setBounds (coord-x (.id piece)) (coord-y (.id piece)) 50 75)
    (.setBackground (Color. 241 221 196 255))
    (.setBorderPainted false)
    (.setName (str (.id piece)))
    (.addActionListener choose-piece)))

(defn unused-pieces-buttons []
  (map place-piece (get @state :unused-pieces)))

     

(defn end 
  [win? winner]
  (if win? 
    (.setText message-label (str (name winner) " won!")
    (.settext message-label "Game over without winner!"))))