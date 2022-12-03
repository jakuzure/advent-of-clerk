;; # 🎄 Advent of Clerk: Day 3
(ns advent-of-clerk.day-03
  (:require [nextjournal.clerk :as clerk]))

(def input
  "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw")

(def split-lines
  (clojure.string/split-lines input))

(defn half-input [l]
  (split-at (/ (count l) 2) l))

;recursive function that takes n strings would be nicer
(defn find-matching-chars [s1 s2]
  (distinct (filter (into #{} s1) s2)))

(def find-all-errors
  (flatten
    (map
      #(find-matching-chars (first %) (last %)) (map half-input split-lines))))

(defn char->int [n]
  (if (>= (int n) 97)
    (- (int n) 96) ;lower
    (- (int n) 38))) ;upper))

(def answer-part1
  (reduce + (map char->int find-all-errors)))

(defn find-elf-groups [coll]
  (let [[a b c] coll]
    (find-matching-chars (find-matching-chars a b) c)))

(def answer-part2
  (->> (flatten (map find-elf-groups (partition 3 split-lines)))
      (map char->int)
      (reduce +)))
