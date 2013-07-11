(ns quarto.gui
  (:use quarto.board) 
  (:import
    (java.awt Color Dimension Image BorderLayout)
    (javax.swing JPanel JFrame ImageIcon JButton JLabel JTextField JOptionPane)
    (java.awt.event ActionListener MouseListener MouseMotionListener)
    javax.imageio.ImageIO
    java.io.File)
  (:require [clojure.java.io :as io]))

(def img-url (io/resource "gameboard.png"))

(defn piece-url 
  [piece]
  (io/resource (str "p" (piece-id piece) "e.png")))

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

(defn get-x
  []
  nil)

(defn get-y
  []
  nil)

(defn get-chosen-piece []
  nil)

(defn get-name 
  [text-filed]
  (.getText text-filed))

(defn names-window []
  (let [panel (JPanel.)]
        ; name-field1 (JTextField. "Player1" 20)
        ; name-field2 (JTextField. "Player2" 20)
        ; submit-button (JButton. "OK")
        ; frame (proxy [JFrame ActionListener] []
        ;         (actionPerformed [e]
        ;           (if (= JOptionPane/YES_OPTION (JOptionPane/showConfirmDialog nil (str "Ready to start a game?")))
        ;             (run))))]
    (doto panel
      (.add (JLabel. "Some text")))
      ; (.add (JLabel. "First player:"))
      ; (.add name-field1)
      ; (.add (JLabel. "Second player:"))
      ; (.add name-field2))
      ; (.add submit-button)
    (doto (JFrame. "Quarto Game")
      (.add panel BorderLayout/NORTH)
      (.add game-panel BorderLayout/CENTER)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true)
      (.setSize (Dimension. 830 555)))))
    ; (.addActionListener submit-button frame)))