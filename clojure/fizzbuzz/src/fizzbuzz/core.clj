(ns fizzbuzz.core
  (:use [clojure.test]))

(defn make-test [mod response]
  (fn [num] (if (= (rem num mod) 0)
              (name response)
              num)))

(def fizz (make-test 3 :fizz))
(def buzz (make-test 5 :buzz))

(def fizzbuzzer )

(defn fizzbuzz [numbers])
