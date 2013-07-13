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


(def message-label 
  (doto (JLabel.)
    (.setText "Some Text")))

(def img-url (io/resource "gameboard.png"))

(defn piece-url 
  [piece]
  (io/resource (str "p" (.id piece) "e.png")))

(def image-background (.getImage (ImageIcon. img-url)))

(def game-panel
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))))

(def panel 
  (doto (JPanel.)
     (.add message-label)))

(def frame 
  (doto (JFrame. "Quarto Game")
     (.add panel BorderLayout/NORTH)
     (.add game-panel BorderLayout/CENTER)      
     (.pack)
     (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
     (.setVisible true)
     (.setSize (Dimension. 830 555))))


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
    (.setBounds 640 40 50 75)
    (.setBackground (Color. 241 221 196 255))
    (.setBorderPainted false)
    (.setName "select")))

(defn disable-button [button]
    (do
      (.setEnabled button false)
      (.setVisible button false)))

(defn move-to-selected [event-source]
  (let [id (Integer. (.getName event-source))
        unused (get @state :unused-pieces)
        coincidence-coll (vec (filter #(= id (.id %)) unused))
        moved-piece (coincidence-coll 0)]
    (do (prn moved-piece)
        (disable-button event-source)
        (select-piece moved-piece)
        ; (for [button unused-pieces-buttons]
        ;   (.removeActionListener button choose-piece))
        ; (for [place empty-places-buttons]
        ;   (.addActionListener button choose-place))
        (.. frame getContentPane (add (select-piece-button moved-piece)))
        (prn "ok"))))

(defn img-selected-piece 
  [x y]
  (doto (ImageIcon. (piece-url selected-piece))
    (.setBounds x y 50 75)
    (.setVisible true)))

(defn place-x [x]
  (+ (* x 100) 90))

(defn place-y [y]
  (+ (* y 80) 80))

(defn set-piece [event-source]
  (let [id (Integer. (.getName event-source))
        place-x (places-x id)
        place-y (places-y id)]
    (img-selected-piece place-x place-y)
    (.. frame setIconImage (getImage img-selected-piece))
    (put-piece selected-piece place-x place-y)
    (disable-button select-piece-button)
    (set! empty-places #(disj (set %) id))
    ; (if-not (end?)
    ;   (do 
    ;     (for [button unused-pieces-buttons]
    ;       (.addActionListener button choose-piece))
    ;     (for [place empty-places-buttons]
    ;       (.removeActionListener place choose-place))))
  ))

(def choose-piece 
  (proxy [ActionListener] []
    (actionPerformed [event] (do (prn "action!")
                                 (if (nil? selected-piece)
                                    (do (prn "select")
                                        (move-to-selected (.getSource event))))))))

(def choose-place 
  (proxy [ActionListener] []
    (actionPerformed [event] (do (prn "action!")
                                 (if-not (nil? selected-piece)
                                   (set-piece (.getSource event)))))))

(defn x-coord [id]
  (+ (* (places-x id) 85) 108))

(defn y-coord [id]
  (+ 115 (* (places-y id) 80)))

(defn place-button
  [place]
  (doto (JButton.)
    (.setBounds (x-coord place) (y-coord place) 55 42)
    (.setBackground (Color. 0 0 0 255))
    (.setBorderPainted false)
    (.setName (str place))
    (.addActionListener choose-place)))

(defn place-piece
  [piece]
  (doto (JButton. (ImageIcon. (piece-url piece)))
    (.setBounds (coord-x (.id piece)) (coord-y (.id piece)) 50 75)
    (.setBackground (Color. 241 221 196 255))
    (.setBorderPainted false)
    (.setName (str (.id piece)))
    (.addActionListener choose-piece)))

(def empty-places-buttons
  (let [places @empty-places]
    (map place-button places)))

(def unused-pieces-buttons 
  (map place-piece (get @state :unused-pieces)))

(defn window []
  (let [
        ; button (doto (JButton. (ImageIcon. (io/resource "p0e.png")))
        ;          (.setBounds 520 135 50 75)
        ;          (.setBackground (Color. 241 221 196 255))
        ;          (.setBorderPainted false))
        ]
    (if-not (nil? selected-piece)
      (.. frame getContentPane (add (select-piece-button selected-piece))))
    (for [piece-button unused-pieces-buttons]
      (.. frame getContentPane (add piece-button)))
    (for [place empty-places-buttons]
      (.. frame getContentPane (add place)))))
