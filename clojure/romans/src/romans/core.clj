(ns romans.core)

(def numerals
  {
    1000 "M",
    900  "CM",
    500  "D",
    400  "CD",
    100  "C",
    90   "XC",
    50   "L",
    40   "XL",
    10   "X",
    9    "IX",
    5    "V",
    4    "IV",
    1    "I"
   }
  )
(defn less-than? [x y]
  (>= x y))

(defn detect [pred coll]
  (first (filter pred coll)))

(defn convert-to-roman [num]
  (if (zero? num)
    ""
    (let [[arabic roman] (detect #(less-than? num (first %)) (reverse (into (sorted-set) numerals)))]
      (str roman (convert-to-roman (- num arabic))))))
