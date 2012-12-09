(ns fizzbuzz.test.core
  (:use [fizzbuzz.core])
  (:use [clojure.test]))

(deftest fizz-test
  (is (= (fizz 1) 1))
  (is (= (fizz 3) "fizz"))
  (is (= (fizz 9) "fizz")))

(deftest buzz-test
  (is (= (buzz 1) 1))
  (is (= (buzz 5) "buzz"))
  (is (= (buzz 15) "buzz")))

(deftest fizzbuzz-test
  (is (= (fizzbuzz [1 2 3 4 5]) [1 2 "fizz" 4 "buzz"]))
  (is (= (fizzbuzz (take 20 (range 1 11)))
         [1 2 "fizz" 4 "buzz" "fizz" 7 8 "fizz" "buzz" 11 "fizz" 13 14 "fizzbuzz"])))
