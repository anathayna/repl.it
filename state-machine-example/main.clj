;; https://clojuredocs.org/clojure.core/case
;; https://morsecode.world/international/translator.html

;; alfabeto: [a-z]

  (defn q1 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q2 (rest cadeia)) ;;e .
      \- (q3 (rest cadeia)) ;;t -
      (q1 cadeia)
      ;\space (q1 (rest cadeia))
    )
  )

  (defn q2 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q4 (rest cadeia)) ;;i ..
      \- (q5 (rest cadeia)) ;;a .-
      (print  "e")
      (q1 cadeia)
      ;\space (do (print  "e")(q1 (rest cadeia)))
    )
  )

  (defn q3 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q6 (rest cadeia)) ;n -.
      \- (q7 (rest cadeia)) ;m --
      (print  "t")
      (q1 cadeia)
      ;\space (do (print  "t")(q1 (rest cadeia)))
    )
  )

  (defn q4 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q8 (rest cadeia)) ;s ...
      \- (q9 (rest cadeia)) ;u ..-
      (print  "i")
      (q1 cadeia)
      ;\space (do (print  "i")(q1 (rest cadeia)))
    )
  )

  (defn q5 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q10 (rest cadeia)) ;r .-.
      \- (q11 (rest cadeia)) ;w .--
      (print  "a")
      (q1 cadeia)
      ;\space (do (print  "a")(q1 (rest cadeia)))
    )
  )

  (defn q6 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q12 (rest cadeia)) ;d -..
      \- (q13 (rest cadeia)) ;k -.-
      (print  "n")
      (q1 cadeia)
      ;\space (do (print  "n")(q1 (rest cadeia)))
    )
  )

  (defn q7 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q14 (rest cadeia)) ;g --.
      \- (q15 (rest cadeia)) ;o ---
      (print  "m")
      (q1 cadeia)
      ;\space (do (print  "m")(q1 (rest cadeia)))
    )
  )

  (defn q8 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q16 (rest cadeia)) ;h ....
      \- (q17 (rest cadeia)) ;v ...-
      (print  "s")
      (q1 cadeia)
      ;\space (do (print  "s")(q1 (rest cadeia)))
    )
  )

  (defn q9 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q18 (rest cadeia)) ;f ..-.
      ;\- (q28 (rest cadeia)) ;fim
      (print  "u")
      (q1 cadeia)
      ;\space (do (print  "u")(q1 (rest cadeia)))
    )
  )

  (defn q10 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q19 (rest cadeia)) ;l .-..
      ;\- (q28 (rest cadeia)) ;fim
      (print  "r")
      (q1 cadeia)
      ;\space (do (print  "r")(q1 (rest cadeia)))
    )
  )

  (defn q11 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q20 (rest cadeia)) ;p .--.
      \- (q21 (rest cadeia)) ;j .---
      (print  "w")
      (q1 cadeia)
      ;\space (do (print  "w")(q1 (rest cadeia)))
    )
  )

  (defn q12 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q22 (rest cadeia)) ;b -...
      \- (q23 (rest cadeia)) ;x -..-
      (print  "d")
      (q1 cadeia)
      ;\space (do (print  "d")(q1 (rest cadeia)))
    )
  )

  (defn q13 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q24 (rest cadeia)) ;c -.-.
      \- (q25 (rest cadeia)) ;y -.--
      (print  "k")
      (q1 cadeia)
      ;\space (do (print  "k")(q1 (rest cadeia)))
    )
  )

  (defn q14 [cadeia]
    (case (first cadeia)
      ;nil nil
      \. (q26 (rest cadeia)) ;z --..
      \- (q27 (rest cadeia)) ;q --.-
      (print  "g")
      (q1 cadeia)
      ;\space (do (print  "g")(q1 (rest cadeia)))
    )
  )

  (defn q15 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "o")
      (q1 cadeia)
      ;\space (do (print  "o")(q1 (rest cadeia)))
    )
  )

  (defn q16 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "h")
      (q1 cadeia)      
      ;\space (do (print  "h")(q1 (rest cadeia)))
    )
  )

  (defn q17 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "v")
      (q1 cadeia)
      ;\space (do (print  "v")(q1 (rest cadeia)))
    )
  )

  (defn q18 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\- (q28 (rest cadeia)) ;fim
      ;\. (q28 (rest cadeia)) ;fim
      (print  "f")
      (q1 cadeia)
      ;\space (do (print  "f")(q1 (rest cadeia)))
    )
  )

  (defn q19 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "l")
      (q1 cadeia)
      ;\space (do (print  "l")(q1 (rest cadeia)))
    )
  )

  (defn q20 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "p")
      (q1 cadeia)
      ;\space (do (print  "p")(q1 (rest cadeia)))
    )
  )

  (defn q21 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "j")
      (q1 cadeia)
      ;\space (do (print  "j")(q1 (rest cadeia)))
    )
  )

  (defn q22 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\- (q28 (rest cadeia)) ;fim
      ;\. (q28 (rest cadeia)) ;fim
      (print  "b")
      (q1 cadeia)
      ;\space (do (print  "b")(q1 (rest cadeia)))
    )
  )

  (defn q23 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\- (q28 (rest cadeia)) ;fim
      ;\. (q28 (rest cadeia)) ;fim
      (print  "x")
      (q1 cadeia)
      ;\space (do (print  "x")(q1 (rest cadeia)))
    )
  )

  (defn q24 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\- (q28 (rest cadeia)) ;fim
      ;\. (q28 (rest cadeia)) ;fim
      (print  "c")
      (q1 cadeia)
      ;\space (do (print  "c")(q1 (rest cadeia)))
    )
  )

  (defn q25 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\- (q28 (rest cadeia)) ;fim
      ;\. (q28 (rest cadeia)) ;fim
      (print  "y")
      (q1 cadeia)
      ;\space (do (print  "y")(q1 (rest cadeia)))
    )
  )

  (defn q26 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "z")
      (q1 cadeia)
      ;\space (do (print  "z")(q1 (rest cadeia)))
    )
  )

  (defn q27 [cadeia]
    (case (first cadeia)
      ;nil nil
      ;\. (q28 (rest cadeia)) ;fim
      ;\- (q28 (rest cadeia)) ;fim
      (print  "q")
      (q1 cadeia)
      ;\space (do (print  "q")(q1 (rest cadeia)))
    )
  )

  (defn q28 [cadeia]
    (case (first cadeia)
      ;nil nil
      (print  " ")
      (q1 cadeia)
      ;\space (do (print  " ")(q1 (rest cadeia)))
    )
  )

(q1 ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.. ")