(use 'clojure.repl)

(defn meditate
  "Returns uppercase if not calm!"
  [s calm]
  (println "Clojure Meditate v1.0")
  (if calm
    (clojure.string/capitalize s)
    (str (clojure.string/upper-case s) "!")))

; Meditation v2  
(defn meditate2
  "Takes a calmness level and modifies a string to communicate
   accoridng to the calmness leve"
  [s calmness-level]
  (if (< calmness-level 5)
    (str (clojure.string/upper-case s) ", I TELL YA!")
    (if (<= 5 calmness-level 9)
      (clojure.string/capitalize s)
      (when (= calmness-level 10)
        (clojure.string/reverse s)))))

(meditate2 "what we do no echoes in eternity" 1)

; Meditate usinng (cond)
(defn meditate3
  "Takes a calmness level and modifies a string to communicate
   accoridng to the calmness leve"
  [s calmness-level]
  (cond
    (< calmness-level 5) (str (clojure.string/upper-case s) ", I TELL YA!")
    (<= 5 calmness-level 9) (clojure.string/capitalize s)
    (= calmness-level 10) (clojure.string/reverse s)))