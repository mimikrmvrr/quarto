(ns quarto.gui
  ; (:use 
  ;   quarto.states-and-moves
  ;   quarto.biard)
  (:import
    (java.awt Color Dimension Image)
    (javax.swing JPanel JFrame ImageIcon)
    (java.awt.event ActionListener MouseListener MouseMotionListener)
    javax.imageio.ImageIO
    java.io.File))

(def panel 
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.drawImage g (.getImage (ImageIcon. "gameboard.png")) 
                  0 0 (.getWidth this) (.getHeight this) nil))))

(defn run [] 
  (doto (JFrame. "Quarto Game")
    (.add panel)
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