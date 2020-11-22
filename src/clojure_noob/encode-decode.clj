(use 'clojure.repl)

; The Obfuscation Machine
;; You need to develop an encode function and a decode function. The encode function
;; should replace letters with numbers that are not easily guessable. For that purpose
;; each letter will take the character's number value in the ASCII table, add another
;; number to it (the number of words in the sentence to encode), and finally, compute the
;; square value of that number. The decode function should allow the user to revert to the
;; original string. Someone highly ranked in the agency came up with that algorithm so
;; they trust it to be very secure.

;; We'll use clojure.string/replace. From the docs: 
;; 
;; clojure.string/replace
;; ([s match replacement])
;;   Replaces all instance of match with replacement in s.

;;    match/replacement can be:

;;    string / string
;;    char / char
;;    pattern / (string or function of match).

;;    See also replace-first.

;;    The replacement is literal (i.e. none of its characters are treated
;;    specially) for all cases above except pattern / string.

;;    For pattern / string, $1, $2, etc. in the replacement string are
;;    substituted with the string that matched the corresponding
;;    parenthesized group in the pattern.  If you wish your replacement
;;    string r to be used literally, use (re-quote-replacement r) as the
;;    replacement argument.  See also documentation for
;;    java.util.regex.Matcher's appendReplacement method.

;;    Example:
;;    (clojure.string/replace "Almost Pig Latin" #"\b(\w)(\w+)\b" "$2$1ay")
;;    -> "lmostAay igPay atinLay"
;;    
;;    ---
;;    For pattern / function match, patterns means we can use regex, and replace
;;    with whatever our function returns.
;;    
;;    We'll break the encoding step into two functions:
;;       1. Encode the invdividual letters (with char-array and int)
;;       2. Use replace to run the function in 1. and replace every letter with its
;;          encoded counterpart. 


(defn encode-letter
  "Encode individual characters to random int"
  [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    ;Math/pow returns a float, so we force into an int and then a string
    (str "#" (int code))))

(encode-letter "s" 4)
;; => "#14161"

(defn encode-phrase
  [s]
  (let [word-count (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w" (fn [s] (encode-letter s word-count)))))
; s will be passed to function.

(encode-phrase "Hello World")
;; => "#5476#10609#12100#12100#12769 #7921#12769#13456#12100#10404"

;; If we write only (encode-letter s) looks like it
;; doesn't work - this is because the ab2ove expression returns a character,
;; while in other cases we return a function, the replace iterates over it. 

;; We'll now write a reverse decoding function! 
;; Math/sqrt to obtain the square root value of a number.
;; char to retrieve a letter from a character code (a number).
;; subs as in substring, to get a sub-portion of a string (and get rid of our # separator).
;; Integer/parseInt to convert a string to an integer

(defn decode-letter
  [el y]
  (let [number (Integer/parseInt (subs el 1))
        letter (char (- (Math/sqrt number) y))]
    (str letter)))


(defn decode-phrase
  [es]
  (let [word-count (count (clojure.string/split es #" "))]
    (clojure.string/replace es #"\#\d+" (fn [es] (decode-letter es word-count)))))


(decode-phrase "#5476#10609#12100#12100#12769 #7921#12769#13456#12100#10404")
;; => "Hello World"
;;