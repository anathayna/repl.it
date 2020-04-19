(require '[clojure.string :as str])

;; cria uma lista com elementos da lista original repetido N vezes
;; exemplo [1 2 3 5] x 2 => [[1 1] [2 2] [3 3] [5 5]]
(defn cria-listas-de-listas [n lista] (map (partial repeat n) lista))

(defn produto-cartesiano [conj1, conj2] 
  ;; aqui usa-se o conceito do map de vector
  ;; (vector 1 3 5) => [1 3 4]
  ;; (map vector [1 3 4] [6 7 8]) => [[1 6] [3 7] [4 8]]
  ;; parcial permite gerar uma função com um argumento fixo para usar 
  ;; 
  ;; exemplo: (map (partial sum +) [1 2 3]) => [2 3 4]
  ;; apply concat => remover os elementos dentro de elementos
  (apply concat (map (partial map vector conj1) (cria-listas-de-listas (count conj1) conj2)))
)

(println "Insira o conjunto 1, com elementos separados por espaco:")
(let [conjunto1 (str/split (read-line) #"\s+")]
  (println "Insira o conjunto 2, com elementos separados por espaco:")
  (let [conjunto2 (str/split (read-line) #"\s+")]
    (println (str "Conjunto 1: " (str conjunto1)))
    (println (str "Conjunto 2: " (str conjunto2)))
    (produto-cartesiano conjunto1 conjunto2)
  )
  )

;; (map vector [1 3 4] [4 5 6])
;; (map (partial vector 2) [5 6 7]
;; (map (partial map vector [1 3 4]) [[1 1 1] [2 2 2] [3 3 3]])