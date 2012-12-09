(ns prime-factors.core)

(defn detect [pred coll]
  (first (filter pred coll)))

(defn primes [num]
  (if (<= num 1)
    []
    (let [factor (detect #(zero? (rem num %)) (range 2 (+ num 1)))]
      (flatten [factor (primes (/ num factor))]))))
