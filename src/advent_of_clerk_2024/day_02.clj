;; # 🎄 Advent of Clerk: Day 2
(ns advent-of-clerk-2024.day-02
  (:require [nextjournal.clerk :as clerk]
            [clojure.test :refer [deftest is run-tests testing]]))

(def input
  "7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9")

(defn parse-input [input-str]
  (mapv (fn [line]
          (mapv #(Integer/parseInt %)
                (clojure.string/split line #"\s+")))
        (clojure.string/split-lines input-str)))

(def parsed (parse-input input))

(defn is-safe? [levels]
  (let [differences (map - (rest levels) levels)
        diffs (map #(Math/abs %) differences)
        diffs-valid? (every? #(and (>= % 1) (<= % 3)) diffs)
        increasing? (every? pos? differences)
        decreasing? (every? neg? differences)]
    (and diffs-valid? (or increasing? decreasing?))))

(def answer1 (count (filter true? (map is-safe? parsed))))

(defn is-safe-with-removal? [levels]
  (some is-safe?
        (cons levels
            (for [i (range (count levels))]
              (vec (concat (subvec levels 0 i) (subvec levels (inc i))))))))

(def answer2 (count (filter true? (map is-safe-with-removal? parsed))))

(deftest test-answer1
  (is (= answer1 2)))

(deftest test-answer2
  (is (= answer2 4)))

(test-answer1)
(test-answer2)

(run-tests)