;; # 🎄 Advent of Clerk: Day 6
(ns advent-of-clerk.day-06
  (:require [nextjournal.clerk :as clerk]
            [clojure.test :refer [deftest is run-tests]]))

(def input
 "mjqjpqmgbljsphdztnvjfqwrcgsmlb")

(defn generic-marker-finder [s length]
  (loop [position 0]
    (let [end-pos (+ position length)
          sub (distinct (subs s position end-pos))]
      (if (= length (count sub))
        end-pos
       (recur (inc position))))))

(defn start-of-packet-marker [s]
  (generic-marker-finder s 4))

(deftest example-part1-1 (is (= (start-of-packet-marker
                                 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7)))

(deftest example-part1-2 (is (= (start-of-packet-marker
                                 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5)))

(deftest example-part1-3 (is (= (start-of-packet-marker
                                 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10)))

(deftest example-part1-4 (is (= (start-of-packet-marker
                                  "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))

(deftest example-part1-5 (is (= (start-of-packet-marker
                                  "nppdvjthqldpwncqszvftbrmjlhg") 6)))

(def answer-part1
  (start-of-packet-marker input))

(defn start-of-message-marker [s]
  (generic-marker-finder s 14))

(deftest example-part2-1 (is (= (start-of-message-marker
                                  "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19)))

(deftest example-part2-2 (is (= (start-of-message-marker
                                  "bvwbjplbgvbhsrlpgdmjqwftvncz") 23)))

(deftest example-part2-3 (is (= (start-of-message-marker
                                  "nppdvjthqldpwncqszvftbrmjlhg") 23)))

(deftest example-part2-4 (is (= (start-of-message-marker
                                  "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29)))

(deftest example-part2-5 (is (= (start-of-message-marker
                                  "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26)))

(def answer-part2
  (start-of-message-marker input))

(run-tests)
