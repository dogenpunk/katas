(ns codebreaker.core)

(defn repeat-char [c num]
  (take num (repeat c)))

(defn pluses [n]
  (apply str (repeat-char \+ n)))

(defn minuses [n]
  (apply str (repeat-char \- n)))

(defn count-if [f coll]
  (count (filter f coll)))

(defn count-exact-matches
  [guess secret]
  (count-if true? (map = guess secret)))

(defn count-inexact-matches
  [guess secret]
  (let [guess guess
        secret secret
        res 0]
    (count-if true? (map #(in-coll? % secret) guess))))

(defn in-coll?
  [elem coll]
  (boolean (some #{elem} coll)))

(defn score-guess [guess secret]
  (let [gs (vec (str guess))
        st (vec (str secret))
        exact (count-exact-matches gs st)
        inexact (count-inexact-matches gs st)]
    (str (pluses exact) (minuses (- inexact exact)))))
