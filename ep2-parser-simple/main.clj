;; https://clojuredocs.org/clojure.core/case
;; https://morsecode.world/international/translator.html
;; https://craftinginterpreters.com/parsing-expressions.html
;; https://craftinginterpreters.com/representing-code.html
;; https://clojuredocs.org/clojure.core/pop

;; Emissão:
;; Número #0
;; Operador unário negativo 
;; Operador parênteses
;; Operador potência
;; Operador divisão
;; Operador multiplicação
;; Operador soma
;; Operador diferença

;; Expressão: (z "-2*56/5^(7+0-2)*3")

;; i -> [0-9]+

(defn i [cadeia]
  (println cadeia)
  (let [valor (re-find #"^\d+" cadeia)] 
    (if 
      (nil? valor) 
      (println "falha na verificação :(")
      (do 
        (println "match:" valor)
        (clojure.string/replace cadeia #"^\d+" "")
      )
    )
  )
)

;; u -> i | -u

(defn u [cadeia]
  (if 
    (= \- (first cadeia))
    (do
      (println "unário negativo")
      (u (subs cadeia 1))
     ) 
    (i cadeia) 
  )
)

;; m -> m*n | u

(defn m [cadeia] 
  (if 
    (= \* (first cadeia))
    (do
      (m (u (subs cadeia 1)))
      (println "multiplicação")
      cadeia  
    )
  )
)

(defn n [cadeia] 
  (let [valor (u cadeia)]
    (m valor)
  )
)

;; e -> e/n | t

(defn e [cadeia] 
  (if 
    (= \/ (first cadeia))
    (e (n (subs cadeia 1)))
    (do
      (println "divisão")
      cadeia  
    )
  )
)

(defn t [cadeia] 
  (let [valor (n cadeia)]
    (e valor)
  )
)

;; w -> w^t | o 

(defn w [cadeia] 
  (if 
    (= \^ (first cadeia))
    (w (t (subs cadeia 1)))
    (do
      (println "potência")
      cadeia  
    )  
  )
)

(defn o [cadeia] 
  (let [valor (t cadeia)]
    (w valor)
  )
)

;; b -> b+o | c

(defn b [cadeia] 
  (if 
    (= \+ (first cadeia))
    (b (o (subs cadeia 1)))
    (do
      (println "soma")
      cadeia  
    )
  )
)

(defn c [cadeia] 
  (let [valor (o cadeia)]
    (b valor)
  )
)

;; h -> h-c | j

(defn h [cadeia] 
  (if 
    (= \- (first cadeia))
    (h (c (subs cadeia 1)))
    (do
      (println "diferença")
      cadeia  
    )
  )
)

(defn j [cadeia] 
  (let [valor (c cadeia)]
    (h valor)
  )
)

;; k -> (k | q

(defn k [cadeia] 
  (if 
    (= \( (first cadeia))
    (k (j (subs cadeia 1)))
    (do
      (println "abre parênteses")
      cadeia  
    )
  )
)

(defn q [cadeia] 
  (let [valor (j cadeia)]
    (k valor)
  )
)

;; s -> s) | z

(defn s [cadeia] 
  (if 
    (= \) (first cadeia))
    (s (q (subs cadeia 1)))
    (do
      (println "fecha parênteses")
      cadeia  
    )
  )
)

(defn z [cadeia] 
  (let [valor (q cadeia)]
    (s valor)
  )
)

(z "-2*56/5^(7+0-2)*3")