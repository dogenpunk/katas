(ns prime-factors.test.core
  (:use [prime-factors.core])
  (:use [clojure.test]))

(deftest replace-me ;; FIXME: write
  (is (= (primes 1) []))
  (is (= (primes 2) [2]))
  (is (= (primes 3) [3]))
  (is (= (primes 4) [2,2]))
  (is (= (primes 5) [5]))
  (is (= (primes 6) [2,3]))
  (is (= (primes 7) [7]))
  (is (= (primes 8) [2,2,2]))
  (is (= (primes 9) [3,3])))
