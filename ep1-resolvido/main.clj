(require '[clojure.string :as str])

(def alfabeto {
  ".-"      "a"
  "-..."    "b"
  "-.-."    "c"
  "-.."     "d"
  "."       "e"
  "..-."    "f"
  "--."     "g"
  "...."    "h"
  ".."      "i"
  ".---"    "j"
  "-.-"     "k"
  ".-.."    "l"
  "--"      "m"
  "-."      "n"
  "---"     "o"
  ".--."    "p"
  "--.-"    "q"
  ".-."     "r"
  "..."     "s"
  "-"       "t"
  "..-"     "u"
  "...-"    "v"
  ".--"     "w"
  "-..-"    "x"
  "-.--"    "y"
  "--.."    "z"
  "-----"   "0"
  ".----"   "1"
  "..---"   "2"
  "...--"   "3"
  "....-"   "4"
  "....."   "5"
  "-...."   "6"
  "--..."   "7"
  "---.."   "8"
  "----."   "9"
})

(defn tradutor-morse [codigo-morse] 
  (for [x codigo-morse]
    (if (get alfabeto x) (alfabeto x) nil)
  )  
)

(println "Entre com o código morse usando ponto, traço e espaços")
(let [codigo-morse (str/split (read-line) #" ")]
  (println (tradutor-morse codigo-morse))
)