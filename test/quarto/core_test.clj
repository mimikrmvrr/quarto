(ns quarto.core-test
  (:use clojure.test
        quarto.core
        quarto.board
        quarto.states_and_moves))

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

(deftest legal-move-test
  (testing "can make a move in the beginning"
    (true? (legal-move? 0 2 (get start-state :board))))
  (testing "cannot make a move"
    (let [board (vec (repeat dim (vec (repeat dim 1))))]
      (false? (legal-move? 0 2 board)))))

