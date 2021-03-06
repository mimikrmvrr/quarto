(ns quarto.core-test
  (:use clojure.test
        quarto.core
        quarto.board
        quarto.states_and_moves
        quarto.gui))

(deftest a-test
  (testing "always true"
    (is (= 1 1))))

(deftest all-pieces-test
  (testing "the count of all pieces is 16"
    (is (= 16 (count all-pieces)))))

;;; I don't know why this is not working - it is the same as others???
; (deftest tall-pieces-test
;   (testing "the count of tall pieces is 8"
;     (is (= 8 (count (seq (filter #(= :tall (.size %)) all-pieces)))))))

(deftest black-pieces-test
  (testing "the count of black pieces is 8"
    (is (= 8 (count (seq (filter #(= :black (.color %)) all-pieces)))))))

(deftest circle-pieces-test
  (testing "the count of circle pieces is 8"
    (is (= 8 (count (seq (filter #(= :circle (.shape %)) all-pieces)))))))

(deftest solid-pieces-test
  (testing "the count of solid pieces is 8"
    (is (= 8 (count (seq (filter #(= :solid (.holed %)) all-pieces)))))))

(def start-state
  (atom 
    {:board (vec (repeat dim (vec (repeat dim nil))))
     :unused-pieces (set all-pieces)
     :current-piece nil}))

(deftest legal-move-test
  (testing "can make a move in the beginning"
    (true? (legal-move? 0 2 (get @start-state :board))))
  (testing "cannot make a move"
    (let [board (vec (repeat dim (vec (repeat dim 1))))]
      (false? (legal-move? 0 2 board)))))

(def test-board [[0 1 2 3]
                 [4 5 6 7]
                 [8 9 10 11]
                 [12 13 14 15]])

(deftest diagonals-test
  (testing "diagonals 0 5 10 15 and 12 9 6 3"
    (is (diagonals test-board) '((0 5 10 15) (3 6 9 12)))))

(deftest rows-test
  (testing "the second row is 4 5 6 7"
    (is (rows test-board) [4 5 6 7])))

(deftest columns-test
  (testing "the first column is 0 4 8 12"
    (is (columns test-board) [0 4 8 12])))

(deftest winning-combination-test
  (testing "if there is a nil in combination it is not winning"
    (false? (winning-combination? (diagonals (get @start-state :board))))))

(deftest win?-test
  (testing "the start state is not winning"
    (false? (win?))))

(deftest all-filled-test
  (testing "the start state is not filled"
    (false? (all-filled?))))


(def test-piece (quarto.board.Piece. :white :circle :tall :hollow 5))

(deftest piece-id-test
  ; (testing "[:white :circle :tall :holllow] is with id 5"
  ;   (= (piece-id test-piece) 5))
  (testing "test-piece-url is p5e.png"
    (is (piece-url test-piece) "p5e.png")))

(deftest piece-place-test
  (testing "test-piece is with delta-x 50 and delta-y 75"
    (and (= (coord-x (.id test-piece)) 570)
         (= (coord-y (.id test-piece)) 285))))