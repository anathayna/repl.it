;; https://clojuredocs.org/clojure.core/case
;; re-matches #"\d" (str (first cadeia)))
;; (re-find #"^\d+" "456+345")

;; (get "foo" 0) => get first character
;; (subs "Clojure" 1)  => remove first character

;; https://craftinginterpreters.com/parsing-expressions.html

;; Gramática do Projeto
;; M = M * S | S
;; S = S * U | U
;; U = -U | I
;; I => [0-9]+

;; Gramática LL
;; M => S(+S)*
;; S => U(xU)*
;; U => -U | I
;; I => [0-9]+

(defn i [cadeia]
  (let [valor (re-find #"^\d+" cadeia)] 
    (if 
      (not (nil? valor))
      (do 
        (println "Match valor:" valor)
        (clojure.string/replace cadeia #"^\d+" "")
      )
      (println "!!!!! Falhou Match número !!!!")
    )
  )
)

(defn u [cadeia]
  (if 
    (= "-" (str (first cadeia)))
    (do 
      (println "UNÁRIO NEGATIVO")
      (u (subs cadeia 1))
    )
    (i cadeia)
  )
)

(defn m1 [cadeia]
  ;;(println "Desceu para m1")
  ;;(println "Cadeia m1:" cadeia)
  (if
    (= "*" (str (first cadeia)))
    (do 
      ;;(println "Match m:" cadeia)
      (recur (u (subs cadeia 1))))
    (do 
      (println "MULTIPLICACAO")
      cadeia
    )
  )
)

(defn m [cadeia]
  ;;(println "Desceu para m")
  ;;(println "Cadeia m:" cadeia)
  (let [valor (u cadeia)]
    (m1 valor)
  )
)

(defn s1 [cadeia] 
  ;;(println "Desceu para s1")
  ;;(println "Cadeia s1:" cadeia)
  (if
    (= "+" (str (first cadeia)))
    (recur (m (subs cadeia 1)))
    (do 
      (println "SOMA")
      cadeia
    )
  )
)

(defn s [cadeia]
  ;;(println "Desceu para s")
  (let [valor (m cadeia)]
    (s1 valor)
  )
)

(println "9+2*23")
(s "9+2*23")
(println "------------------------")
(println "-10+13*---8")
(s "-10+13*---8")