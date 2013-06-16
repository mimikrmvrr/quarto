(ns quarto.core
  (:gen-class))

(def dim 4)

(defrecord Piece[shape size color holed])

(def all-pieces
  (for [shape [:square :circle]
        size  [:tall :short]
        color [:black :white]        
        holed [:hollow :solid]]
    (Piece. shape size color holed)))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
