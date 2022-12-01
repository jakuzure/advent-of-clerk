;; # 🎄 Advent of Clerk: Day 1
(ns advent-of-clerk.day-01
  (:require [nextjournal.clerk :as clerk]))

(comment
  (clerk/serve! {:watch-paths ["src"]}))

(def caloriestring
  "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000")
(def split-lines
  (clojure.string/split-lines caloriestring))

(defn intOrZero [n]
  (if (= "" n)
    0
    (Integer/parseInt n)))

(def string->numbers
  (map intOrZero split-lines))

(def partitioned-vectors
  (partition-by #(= 0 %) string->numbers))

(def reduce-reverse
  (-> (map #(reduce + %) partitioned-vectors)
      sort
      reverse))

(def answer1 (first reduce-reverse))

(def answer2 (reduce + (take 3 reduce-reverse)))
