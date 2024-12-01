;; # 🎄 Advent of Clerk: Day 1
(ns advent-of-clerk-2024.day-01
  (:require [nextjournal.clerk :as clerk]
            [clojure.test :refer [deftest is run-tests testing]]
            [clojure.data]))

(comment
  (clerk/serve! {:watch-paths ["src"]}))

(def input
  "3   4\n4   3\n2   5\n1   3\n3   9\n3   3")

(defn sort-columns [data]
  (let [lines (clojure.string/split-lines data)
        pairs (map #(map read-string (clojure.string/split % #"\s+")) lines)
        first-nums (sort (map first pairs))
        second-nums (sort (map second pairs))]
    [first-nums second-nums]))

(def lists (sort-columns input))

(defn calc-difference [a b]
  (reduce + (map #(Math/abs (- %2 %1)) a b)))

(def answer1 (calc-difference (first lists) (second lists)))

(def freq-map (frequencies (second lists)))

(def total-sum
  (reduce + (map (fn [n]
                   (* n (get freq-map n 0)))
                 (first lists))))

(def answer2 total-sum)

(deftest test-answer1
  (is (= answer1 11)))

(deftest test-answer2
  (is (= answer2 31)))

(test-answer1)
(test-answer2)

(run-tests)