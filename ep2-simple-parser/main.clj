;; https://clojuredocs.org/clojure.core/case
;; https://morsecode.world/international/translator.html
;; https://craftinginterpreters.com/parsing-expressions.html

;; Pop => retorna toda a pilha
;; https://clojuredocs.org/clojure.core/pop

(defn q2 [cadeia, pilha]
  (case [(first cadeia) (last pilha)]
    [nil nil] nil
    [\b "x"] (q2 (rest cadeia) (pop pilha))
  )
)


(defn q1 [cadeia, pilha]
  (case [(first cadeia) (last pilha)]
    [nil nil] nil
    [\a nil] (q1 (rest cadeia) (conj pilha "x"))
    [\a "x"] (q1 (rest cadeia) (conj pilha "x"))
    [\c "x"] (q2 (rest cadeia) pilha)
  )
)

;; linguagem aceita (a^n)c(b^n) n > 1
;; Alfabeto da pilha [x]
(q1 "aaaaacbbbbb" [])