(ns quarto.board)

;==========pieces=======
(defrecord Piece[color shape size holed id])

(def property-number
  {:black 8
   :circle 4
   :short 2
   :hollow 1
   :white 0
   :square 0
   :tall 0
   :solid 0})

(def all-pieces
  (for [color [:black :white]
        shape [:circle :square]
        size  [:short :tall]                
        holed [:hollow :solid]]
    (Piece. color shape size holed (+ (property-number color)
                                      (property-number shape)
                                      (property-number size)
                                      (property-number holed)))))

(def properties [:color :shape :size :holed])

;=========board===============
(def dim 4)

(defn diagonals
  [board]
  (let [x (range dim)]
    (for [y [(range dim) (range (- dim 1) -1 -1)]]
      (map #(get-in board [%1 %2]) x y))))

(defn rows
  [board]
  (for [x (range dim)] (get board x)))

(def columns-indexes-helper
  (for [x (range dim)]
    (vec (repeat dim x))))

(defn columns
  [board]
  (let [y (range dim)]
    (for [x columns-indexes-helper]
      (map #(get-in board [%1 %2]) y x))))

(defn common-properties
  [pieces]
  (let [piece (get pieces 0)]
    (into #{}
      (for [property properties
            :when (= dim (count (seq (filter #(= (property piece) (property %)) pieces))))]
        property))))

(defn winning-combination?
  [pieces]
  (and (not-any? nil? pieces)
       (seq (common-properties pieces))))