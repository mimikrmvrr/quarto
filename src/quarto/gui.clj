(ns quarto.gui
  ; (:use 
  ;   quarto.states-and-moves
  ;   quarto.biard)
  (:import
    (java.awt Color Dimension Image BorderLayout)
    (javax.swing JPanel JFrame ImageIcon JButton JLabel JTextField JOptionPane)
    (java.awt.event ActionListener MouseListener MouseMotionListener)
    javax.imageio.ImageIO
    java.io.File)
  (:require [clojure.java.io :as io]))

(def img-url (io/resource "gameboard.png"))

(def image-background (.getImage (ImageIcon. img-url)))

(def panel 
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))))

(defn run [] 
  (doto (JFrame. "Quarto Game")
    (.setContentPane panel)
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
  (let [name-field1 (JTextField. "Player1" 20)
        name-field2 (JTextField. "Player2" 20)
        submit-button (JButton. "OK")
        panel (JPanel.)
        frame (proxy [JFrame ActionListener] []
                (actionPerformed [e]
                  (if (= JOptionPane/YES_OPTION (JOptionPane/showConfirmDialog nil (str "Ready to start a game?")))
                    (run))))]
    (doto panel
      (.add (JLabel. "First player:"))
      (.add name-field1)
      (.add (JLabel. "Second player:"))
      (.add name-field2))
    (doto frame
      (.add panel BorderLayout/CENTER)
      (.add submit-button BorderLayout/SOUTH)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true)
      (.setSize (Dimension. 800 600)))
    (.addActionListener submit-button frame)))