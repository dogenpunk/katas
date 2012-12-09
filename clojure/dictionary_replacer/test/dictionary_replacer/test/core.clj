(ns dictionary_replacer.test.core
  (:use [dictionary_replacer.core])
  (:use [clojure.test]))

(deftest test-empty-input-string-and-dictionary
  (is (= "" (substitute "" {})))
  (is (= "temporary" (substitute "$temp$" {"temp" "temporary"})))
  (is (= "temporary here comes the name John Doe"
         (substitute "$temp$ here comes the name $name$"
                     {"temp" "temporary"
                      "name" "John Doe"}))))
