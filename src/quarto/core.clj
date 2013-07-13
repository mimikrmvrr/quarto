(ns quarto.core
  (:gen-class)
  (:use [quarto.gui :only (start-window)]))


(defn -main
  [& args]
  (start-window))

