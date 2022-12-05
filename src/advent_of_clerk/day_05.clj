;; # 🎄 Advent of Clerk: Day 5
(ns advent-of-clerk.day-05
  (:require [nextjournal.clerk :as clerk]))

(def input
  "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2")

(def lines (clojure.string/split-lines input))

(def crates-list
  (let [move-lines (map #(clojure.string/split % #" ") (filter #(clojure.string/includes? % "move") lines))
        numbers (map #(map (fn [m] (.replaceAll m "[^0-9]" "")) %) move-lines)
        filtered-nums (map #(filter (fn [x] (not= "" x)) %) numbers)]
    (map #(map (fn [s] (Integer/parseInt s)) %) filtered-nums)))

(def stacks
  [["Z" "N"]
   ["M" "C" "D"]
   ["P"]])

(defn move-crates [crates stack]
  (let [[amount src dest] (first crates)
        to-move (take-last amount (nth stack (dec src)))
        added-stack (vec (flatten (conj (nth stack (dec dest)) (reverse to-move))))
        removed-stack (vec (flatten (drop-last amount (nth stack (dec src)))))
        new-stack (assoc (assoc stack (dec src) removed-stack) (dec dest) added-stack)]
       (if (empty? (rest crates))
         new-stack
         (move-crates (rest crates) new-stack))))

(def answer-part1
  (clojure.string/join (mapcat last (move-crates crates-list stacks))))

(defn move-crates-cranemover9001 [crates stack]
  (let [[amount src dest] (first crates)
        to-move (vec (take-last amount (nth stack (dec src))))
        added-stack (vec (flatten (conj (nth stack (dec dest)) to-move)))
        removed-stack (vec (flatten (drop-last amount (nth stack (dec src)))))
        new-stack (assoc (assoc stack (dec src) (reverse removed-stack)) (dec dest) (reverse added-stack))]
    (if (empty? (rest crates))
      new-stack
      (move-crates (rest crates) new-stack))))

(def answer-part2
  (clojure.string/join
    (mapcat last
            (move-crates-cranemover9001 crates-list stacks))))
