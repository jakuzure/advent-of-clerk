;; # 🎄 Advent of Clerk: Day 4
(ns advent-of-clerk.day-04
  (:require [nextjournal.clerk :as clerk]))

(def input
  "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8")

(def split-lines
  (clojure.string/split-lines input))

(defn split-pair [p]
  (let [pairs (clojure.string/split p #",")
        sp (map #(clojure.string/split % #"-") pairs)
        nums (map #(Integer/parseInt %) (flatten sp))]
    nums))

(defn pair->rangeset [a b]
  (set (range a (inc b))))

(defn pair-contained? [p]
  (let [[a b c d] (split-pair p)
        r1 (pair->rangeset a b)
        r2 (pair->rangeset c d)]
    (or (clojure.set/subset? r1 r2)
        (clojure.set/subset? r2 r1))))

(def answer-part1
  (count (filter pair-contained? split-lines)))

(defn overlap? [p]
  (let [[a b c d] (split-pair p)
        r1 (pair->rangeset a b)
        r2 (pair->rangeset c d)]
    (seq (clojure.set/intersection r1 r2))))

(def answer-part2
  (count (filter overlap? split-lines)))
