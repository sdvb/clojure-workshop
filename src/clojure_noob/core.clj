(ns clojure-noob.core
  (:gen-class))

(use 'clojure.repl)

;; Control Flows
;;; IF 
(if true
  "By Zeus' hammer!"
  "By Aquaman's trident")

(if true
  ;; Do evaluates multiple experssions for true branch
  (do (println "Success!")
      "By Zeus' hammer")
  (do (println "Failure")
      "By Aquaman's tridenr!"))

;;; When
(when true
  (println "Success")
  "abra cadabra")

(defn error-message
  [severity]
  (str "OH GOD, WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED"
         "DOOMED")))

(error-message :mild)
(error-message :strong)

;; Data structures
;;' Numbers
1
2
3

;;; Supports ratios!
1/5
2/5

(+ 1/5 2/5)
;; => 3/5


;;; Strings
"Hello"
"\"Lord Voldemort" \"
;;; '' is not valid for strings - use escape \

;; Vectors 
(def my-list ["Hello" "Sir"])

(get my-list 1)
;; => "Sir"

;; Lists
'("Hello" 2 4) ; Needs a ' at beggining. Cannot use (get) to retrieve items

(nth '("Hello" 2 4) 0)
;; => "Hello"

;; Sets
;;; Sets are collections of unique values
#{1 "hello"}

;; Maps
;;; Like dictionaries or objects
{}
(def person {:name "John"})

;; Functions
;;; Rest parameters
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers] ;rest parameter indicated by &
  (map codger-communication whippersnappers))

(codger "He" "Yo")
;; => ("Get off my lawn, Billy!!!" 
;;     "Get off my lawn, Anne-Marie!!!" 
;;     "Get off my lawn, The Incredible Bulk!!!")



;;; Deconsturcting functions (bind values in function arguments to names)
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println #break (str "Treasure location: " treasure-location)))

 (receive-treasure-location {:lat 48 :lng 56})

(defn list-collector
  [[first-list-item second-list-item]]
  (println (str "This is your first item: " first-list-item)))

(list-collector '("1" "2"))

(defn mapset
  [f list]
  (apply sorted-set (set (into [] (map f list)))))

(mapset inc [1 1 2 2])
;; => #{2 3}




; Predicting atmospheric carbon levels
; Estimate = 382 + ((Year - 2006) * 2)
; 

(def base-co2 382)
(def base-year 2006)

(defn co2-estimate
  "Calculates estimated CO2 levels in a given `year`"
  [year]
  (let [year-diff (- year base-year)]
    (+ base-co2 (* 2 year-diff))))

(co2-estimate 2050)



;; [Done] Maps
;; [Done] Sets
;; Vectors 

