;;https://regexr.com/

;; Exercício baseado no texto:
;; https://purelyfunctional.tv/mini-guide/regexes-in-clojure/

;; . => coringa, qualquer coisa
;; * => zero ou mais
;; + => um ou mais

;; #"" => indica uma regrex
;; Validações
;;(re-matches #"o+abcx*" "zzzabcxxx")
;;(re-matches #"([a-z]+\.)?([a-z]+)\.br" "uol.com.br")

;; Encontra a primeira substring de uma string
(re-find #"^[a-z][a-z_0-9]*[a-z0-9]*$" "_celso_crivelaro12")
(re-find #"^[a-z][a-z_0-9]*$" "celso_crivelaro12")
(re-find #"^[0-9]{2}[/ ?][0-9]{2}[/ ?][0-9]{4} *$" "11/10/1995")
(re-find #"^[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$" "(11) 98765-4321")
(re-find #"^[(]{0,1}[0-9]*[)][-\s\./0-9]*$" "(11) 98765-4321")

;;(re-find #"[0123456789]{8,9}" "980001265")

;; Encontrar todas as substrings que dão match
;;(re-seq #"s+" "mississippi")

;; Replace de um match
;;(clojure.string/replace "mississippi" #"i.." "obb")