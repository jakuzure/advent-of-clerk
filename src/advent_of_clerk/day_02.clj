;; # 🎄 Advent of Clerk: Day 2
(ns advent-of-clerk.day-02
  (:require [nextjournal.clerk :as clerk]))

(def input
  "A Y\nB X\nC Z")

(def lookuptable
  {"X" :A
   "Y" :B
   "Z" :C})

(def tool
  {:A 1
   :B 2
   :C 3})

(defn select-winner [in]
  (let [opponent (keyword (first in))
        self (get lookuptable (last in))
        points (self tool)]
    (+ (cond
         (= self opponent) 3
         (and (= opponent :A) (= self :B)) 6
         (and (= opponent :C) (= self :B)) 0
         (and (= opponent :B) (= self :A)) 0
         :else 1000)
       points)))

(defn select-winner-and-calculate-result [in]
  (let [opponent ((keyword (first in)) tool)
        self ((get lookuptable (last in)) tool)]
    ;points (self tool)]
    (+ (cond
         (= self opponent) 3
         (= 1 (mod (- self opponent) 3)) 6
         :else 0)
       self)))

(def rounds
  (map #(clojure.string/split % #" ") (clojure.string/split-lines input)))

(def total-score-part1
 (reduce +
   (map #(select-winner-and-calculate-result %) rounds)))

(defn outcome-filter [op outcome]
  (filter (fn [res] (= outcome (mod (- res op) 3))) (range 1 4)))

;ugly, but does the job
(defn achieve-required-outcome [in]
  (let [opponent ((keyword (first in)) tool)
        outcome ((get lookuptable (last in)) tool)
        winf (outcome-filter opponent 1)
        losef (outcome-filter opponent 2)]
    (cond
      (= outcome 2) (+ opponent 3)
      (= outcome 3) (+ (first winf) 6) ;win
      (= outcome 1) (first losef)))) ;lose

(def total-score-part2
  (reduce +
    (map #(achieve-required-outcome %) rounds)))
