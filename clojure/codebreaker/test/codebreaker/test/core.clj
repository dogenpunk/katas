(ns codebreaker.test.core
  (:use [codebreaker.core])
  (:use [clojure.test]))


(deftest test-score-guesses
  (are [guess mark] (= mark (score-guess guess 1234))
       5555 ""
       1555 "+"
       2555 "-"
       5254 "++"
       5154 "+-"
       2545 "--"
       5234 "+++"
       5134 "++-"
       5124 "+--"
       5123 "---"
       1234 "++++"
       1243 "++--"
       1423 "+---"
       4321 "----"))

(deftest test-counting-exact-matches
  (are [guess num] (= num (count-exact-matches guess [\1 \2 \3 \4]))
       (vec (str 5555)) 0
       (vec (str 1555)) 1
       (vec (str 5255)) 1
       (vec (str 1535)) 2
       (vec (str 5254)) 2
       (vec (str 1235)) 3
       (vec (str 5234)) 3
       (vec (str 1234)) 4))

(deftest test-counting-inexact-matches
  (are [guess num] (= num (count-inexact-matches guess [\1 \2 \3 \4]))
       (vec (str 5555)) 0
       (vec (str 5155)) 1
       (vec (str 5125)) 2
       (vec (str 5123)) 3
       (vec (str 4321)) 4))

(deftest test-pluses
  (is (= "+" (pluses 1)))
  (is (= "++" (pluses 2)))
  (is (= "" (pluses 0))))

(deftest test-minuses
  (is (= "-" (minuses 1)))
  (is (= "--" (minuses 2)))
  (is (= "" (minuses 0))))
