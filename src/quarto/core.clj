(ns quarto.core
  (:gen-class)
  (:use [quarto.logics :only (start-game)]))


(defn -main
  [& args]
  (start-game))

