(ns quarto.board)


(defrecord Piece[color shape size holed])

(def all-pieces
  (for [color [:black :white]
        shape [:square :circle]
        size  [:tall :short]                
        holed [:hollow :solid]]
    (Piece. color shape size holed)))

(def properties [:color :shape :size :holed])

(def dim 4)

(def start-state
  {:board (vec (repeat dim (vec (repeat dim nil))))
   :unused-pieces (set all-pieces)
   :current-piece nil})

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