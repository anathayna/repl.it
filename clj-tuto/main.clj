(defn greeting
  ([] (greeting "Hello World"))
  ([x] (greeting "Hello" x)) 
  ([x y] (str x ", " y "!")))


(defn do-nothing
  [x]
  x)

(defn always-thing
  [& args]
  (:thing))

(defn make-thingy
  [x]
  (fn [& args] x))

(defn triplicate [f]
  (f) (f) (f))

(defn opposite [f]
  (fn [& args]
      (not (apply f args)))
    
(defn triplicate2 [f & args]
  (triplicate (fn [] (apply f args))))


(defn one-less-arg [f x]
  (fn [& args] (apply f x args))
  
(defn two-fns [f g]
  (fn [x] (f (g x))
      
((fn [x y] (+ x y)) 5 7) 

(defn check-guess [secret guess]
  (if
    (= secret guess)
      "You win!"
      if(< secret guess)
        "Too high"
        "Too low"))
      
(defn check-guess-too [secret guess]
  (cond
    (= secret guess
       "You win!")
    (< secret guess
       "Too low"
       :else
       "Too high"))
  
(defn triplicate3 [f]
  (dotimes [i 3]
           (f)))
         
(defn numbers [n]
  (dotimes [i n])
    (println i)))
  
(defn counting [n] 
  (doseq [i (range 1 (inc n))]
         (println i)))
       
       
(defn print-bands [guitars basses drums]
  (do seq [g guitars
           b basses
           d drums])
         (println g b d)))
       
(defn print-bands-seq [guitars basses drums]
  (for [g guitars
        b basses
        d drums]
      [g b d]))


(defn fizzbuzz []
  (doseq [i (range 1 101)]
    (println (cond (and (zero? (rem i 3))
                        (zero? (rem i 5)))
                      "FizzBuzz"
                      (zero? (rem i 3))
                        "Fizz"
                      (zero? (rem i 5))
                        "Buzz"
                      :else i))))
                    
(defn fizzbuzz-alt []
  (doseq [i range 1 101]
         (let [fizz (if (zero? rem i 3) "Fizz")
               buzz (if (zero? rem i 5) "Buzz")
               number (if (not (or fizz buzz)) i)]
              (println str fizz buzz number))))
            
            
(defn guessing-game []
  (let [secret (rand-int 100)]
       (fn [guess]
           (cond (= secret guess)
                 "You win"
                 (< secret guess)
                 "Too high"
                 :else
                 "Too low")))