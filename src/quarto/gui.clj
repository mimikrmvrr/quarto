(ns quarto.gui
  (:use quarto.board
        seesaw.core) 
  ; (:import
  ;   (java.awt Color Dimension Image BorderLayout)
  ;   (javax.swing JPanel JFrame ImageIcon JButton JLabel JTextField JOptionPane Icon)
  ;   (java.awt.event ActionListener MouseListener MouseMotionListener)
  ;   javax.imageio.ImageIO
  ;   java.io.File)
  (:require [clojure.java.io :as io]))


; (defn draw-board [c g]
;  ; (doseq [cell (living-cells)]
;     (draw g (rect 0 0 5 5) (style :background :green)));)

(defn make-panel []
  (border-panel
    :north (flow-panel :align :center
                       :items [(label :text "TEXT")])
    :center (canvas ;:paint draw-board ;(.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))
                    :class :board
                    :background :red)
    :vgap 5
    :hgap 5
    :border 5))

(defn make-frame []
  (frame :title   "Quarto Game"
         :size    [830 :by 555]
         :content (make-panel)))

(defonce the-frame (make-frame))


(defn start-game []
  (native!)
  (config! the-frame :content (make-panel))
  (show! the-frame))
  ; (add-behaviors the-frame)
  ; (redisplay the-frame))

; (def img-url (io/resource "gameboard.png"))

; (defn piece-url 
;   [piece]
;   (io/resource (str "p" (piece-id piece) "e.png")))

; ; (def piece-images
; ;   {:p0 })

; (def image-background (.getImage (ImageIcon. img-url)))

; (def game-panel
;   (proxy [JPanel] []
;     (paintComponent [g]
;       (proxy-super paintComponent g)
;       (.drawImage g image-background 0 0 (.getWidth this) (.getHeight this) nil))))

; (defn run [] 
;   (doto (JFrame. "Quarto Game")
;     (.setContentPane game-panel)
;     (.setSize (Dimension. 800 600))
;     (.setVisible true)))

(defn get-x
  []
  nil)

(defn get-y
  []
  nil)

(defn get-chosen-piece []
  nil)

; (defn get-name 
;   [text-filed]
;   (.getText text-filed))

; (defn names-window []
;   (let [panel (JPanel.)]
;         ; name-field1 (JTextField. "Player1" 20)
;         ; name-field2 (JTextField. "Player2" 20)
;         ; submit-button (JButton. "OK")
;         ; frame (proxy [JFrame ActionListener] []
;         ;         (actionPerformed [e]
;         ;           (if (= JOptionPane/YES_OPTION (JOptionPane/showConfirmDialog nil (str "Ready to start a game?")))
;         ;             (run))))]
;     (doto panel
;       (.add (JLabel. "Some text")))
;       ; (.add (JLabel. "First player:"))
;       ; (.add name-field1)
;       ; (.add (JLabel. "Second player:"))
;       ; (.add name-field2))
;       ; (.add submit-button)
;     (doto (JFrame. "Quarto Game")
;       (.add panel BorderLayout/NORTH)
;       (.add game-panel BorderLayout/CENTER)
;       (.add (JButton. (ImageIcon. (io/resource "p0e.png"))))
;       (.pack)
;       (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
;       (.setVisible true)
;       (.setSize (Dimension. 830 555)))))
;     ; (.addActionListener submit-button frame)))