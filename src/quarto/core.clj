(ns quarto.core
  (:gen-class)
  (:use [quarto.gui :only (start-game)]))


(defn -main
  [& args]
  (start-game))

