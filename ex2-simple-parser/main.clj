;; I = [0-9]+
;; U = -U | I

;; (i "24") => ""
;; (i "24+4") => +4
;; (i "+4") => nil

(defn i [cadeia]
  (println cadeia)
  (let [valor (re-find #"^\d+" cadeia)]
    (if 
      (nil? valor)
      (println "falha ao verificar :/")
      (do
        (println "match:" valor)
        (clojure.string/replace #"^\d+" valor "")
      )
    )
  )
)

;; (u "23") => ""
;; (u "-23") => ""
;; (u "----23") => ""
;; (u "24+4") => "+4"
;; (u "+4") => nil

(defn u [cadeia]
  (if
    (= \- (first cadeia))
    (do
      (println "negativo")
      (u (subs cadeia 1))
    ) 
    (i cadeia)
  )
)

;; (subs "-23" 1)

;; M = M * U ! U
;; (m "23") => ""
;; (m "-23") => ""
;; (m "----23") => ""
;; (m "24+4") => "+4"
;; (m "*4") => nil
;; (m "4*2#-3") => ""

(defn m [cadeia]
  (let [valor (u cadeia)]
    (if
      (= \* (first valor))
      (m (u (subs valor 1)))
      cadeia
    ) 
  )
)
