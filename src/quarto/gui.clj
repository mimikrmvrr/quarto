(ns quarto.gui
  (:use quarto.board
        quarto.states_and_moves)
        ; (clojure.contrib
        ;   [swing-utils :only (add-action-listener)])) 
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

; (def piece-images
;   {:p0 })

(def image-background (.getImage (ImageIcon. img-url)))

(def game-panel
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))))

(defn run [] 
  (doto (JFrame. "Quarto Game")
    (.setContentPane game-panel)
    (.setSize (Dimension. 800 600))
    (.setVisible true)))

(defn get-name 
  [text-filed]
  (.getText text-filed))

(defn coord-x [id]
  (+ (* (mod id 4) 60) 520))

(defn coord-y [id]
  (+ 150 (* (Math/round (Math/floor (/ id 4))) 75)))

(defn place-piece
  [piece]
  (doto (JButton. (ImageIcon. (piece-url piece)))
    (.setBounds (coord-x (.id piece)) (coord-y (.id piece)) 50 75)
    (.setBackground (Color. 241 221 196 255))
    (.setBorderPainted false)
    (.setName (str (.id piece)))))

(defn unused-pieces-buttons
  [{:keys [unused-pieces]}]
  (map place-piece unused-pieces))

(defn move-to-selected [event-source]
  (let [id (Integer. (.getName event-source))
        moved-piece (get (map #(.id %) unused-pieces) id)]
    (choose-piece-for-onother-player moved-piece)))

(def choose-piece 
  (proxy [ActionListener] []
    (actionPerformed [event] (move-to-selected (.getSource event)))))

(defn names-window []
  (let [panel (doto (JPanel.)
                (.add (JLabel. "Some text")))
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
        ; name-field1 (JTextField. "Player1" 20)
        ; name-field2 (JTextField. "Player2" 20)
        ; submit-button (JButton. "OK")
        ; frame (proxy [JFrame ActionListener] []
        ;         (actionPerformed [e]
        ;           (if (= JOptionPane/YES_OPTION (JOptionPane/showConfirmDialog nil (str "Ready to start a game?")))
        ;             (run))))]
    ; (doto panel
    ;   (.add (JLabel. "Some text")))
      ; (.add (JLabel. "First player:"))
      ; (.add name-field1)
      ; (.add (JLabel. "Second player:"))
      ; (.add name-field2))
      ; (.add submit-button)
    ; (doto (JFrame. "Quarto Game")
    ;   (.add panel BorderLayout/NORTH)
      (for [piece-button (unused-pieces-buttons start-state)]
        (.. frame getContentPane (add piece-button)))))
      ; (.add game-panel BorderLayout/CENTER)      
      ; (.pack)
      ; (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      ; (.setVisible true)
      ; (.setSize (Dimension. 830 555)))))
    ; (.addActionListener submit-button frame)))