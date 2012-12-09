(ns dictionary_replacer.core)

(defn- untokenized [s]
  (clojure.string/replace s "$" ""))

(defn- map-fn [orig dictionary]
  (if (re-matches #"\$\w+\$" orig)
    (dictionary (untokenized orig))
    orig))

(defn substitute [s dict]
  (let [str-coll (re-seq #"[A-Za-z0-9$]+" s)]
    (apply str (interpose  " " (map #(map-fn % dict) str-coll)))))
