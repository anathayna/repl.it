
(def m {[:q0  "X"]   [:q1  "X"  1],
        [:q0  "O"]   [:q8  "O"  1],

        [:q1  "X"]   [:q1  "X"  1],
        [:q1  "#"]   [:q1  "#"  1],
        [:q1  "O"]   [:q2  "A" -1],
        [:q1  "B"]   [:q2  "B" -1],
        [:q1  :B ]   [:q6  :B  -1],
        [:q1  "A"]   [:q14 "A"  1],

        [:q2  "A"]   [:q2  "A" -1],
        [:q2  "X"]   [:q3  "B" -1],
        [:q2  "#"]   [:q4  "#"  1],

        [:q3  :B ]   [:q1  "#"  1],
        [:q3  "X"]   [:q3  "X" -1],
        [:q3  "#"]   [:q3  "#" -1],

        [:q4  "B"]   [:q4  "X"  1],
        [:q4  "A"]   [:q5  "A" -1],

        [:q5  "#"]   [:q0  "#"  1],
        [:q5  "X"]   [:q5  "X" -1],

        [:q6  "A"]   [:q6  :B  -1],
        [:q6  "X"]   [:q6  :B  -1],
        [:q6  "#"]   [:q7  "#" -1],

        [:q7  "#"]   [:q7  "#" 1],

        [:q8  "#"]   [:q8  "#"  1],
        [:q8  "O"]   [:q8  "O"  1],
        [:q8  "X"]   [:q9  "A" -1],
        [:q8  "B"]   [:q9  "B" -1],
        [:q8  :B ]   [:q13 :B  -1],
        [:q8  "A"]   [:q23 "A"  1],

        [:q9  "A"]   [:q9  "A" -1],
        [:q9  "O"]   [:q10 "B" -1],
        [:q9  "#"]   [:q11 "#"  1],

        [:q10 :B ]   [:q8  "#"  1],
        [:q10 "O"]   [:q10 "O" -1],
        [:q10 "#"]   [:q10 "#" -1],

        [:q11 "B"]   [:q11 "O"  1],
        [:q11 "A"]   [:q12 "A" -1],

        [:q12 "#"]   [:q0  "#"  1],
        [:q12 "O"]   [:q12 "O" -1],

        [:q13 "#"]   [:q7  "#" -1],
        [:q13 "A"]   [:q13 :B  -1],
        [:q13 "O"]   [:q13 :B  -1],

        [:q14 "O"]   [:q2  "A" -1],
        [:q14 :B ]   [:q6  :B  -1],
        [:q14 "A"]   [:q14 "A"  1],
        [:q14 "X"]   [:q15 "X" -1],

        [:q15 "A"]   [:q15 "B" -1],
        [:q15 "X"]   [:q15 "B" -1],
        [:q15 "#"]   [:q16 "#"  1],

        [:q16 "B"]   [:q17 "B" -1],

        [:q17 "#"]   [:q18 "B"  1],

        [:q18 "B"]   [:q18 "B"  1],
        [:q18 "X"]   [:q19 "X" -1],
        [:q18 "O"]   [:q19 "O" -1],

        [:q19 "B"]   [:q20 "O" -1],

        [:q20 "#"]   [:q16 "#"  1],
        [:q20 "B"]   [:q20 "B" -1],
        [:q20 :B ]   [:q21 :B   1],

        [:q21 "B"]   [:q21 :B   1],
        [:q21 "O"]   [:q22 "O" -1],
        [:q21 "X"]   [:q22 "X" -1],

        [:q22 :B ]   [:q0  :B   1],

        [:q23 "X"]   [:q9  "A" -1],
        [:q23 :B ]   [:q13 :B  -1],
        [:q23 "A"]   [:q23 "A"  1],
        [:q23 "O"]   [:q24 "O" -1],

        [:q24 "A"]   [:q24 "B" -1],
        [:q24 "O"]   [:q24 "B" -1],
        [:q24 "#"]   [:q25 "#"  1],

        [:q25 "B"]   [:q26 "B" -1],

        [:q26 "#"]   [:q27 "B"  1],

        [:q27 "B"]   [:q27 "B"  1],
        [:q27 "O"]   [:q28 "O" -1],
        [:q27 "X"]   [:q28 "X" -1],

        [:q28 "B"]   [:q29 "X" -1],

        [:q29 "#"]   [:q25 "#"  1],
        [:q29 "B"]   [:q29 "B" -1],
        [:q29 :B ]   [:q30 :B   1],

        [:q30 "B"]   [:q30 :B   1],
        [:q30 "X"]   [:q31 "X" -1],
        [:q30 "O"]   [:q31 "O" -1],

        [:q31 :B ]   [:q0  :B   1]})

(def s [:q0 ["X" "X" "O" "O" "X" "X" "X"] 0])

(defn getNextMove[state moves] 
  (moves [(state 0) ((state 1)(state 2))]))

(defn replaceItemCol [index col item]
  (let [n (count col)
        nthIndex (+ index 1)]
  (vec (concat (take index col) [item] (nthnext col nthIndex))))) 

(defn prependBlank [col] 
  (vec (cons :B col)))

(defn appendBlank [col]
  (vec (conj col :B)))

(defn updateState [state production]
    (let [[currentState tape currentIndex] state
          [nextState newChar moveDirection] production
          maxIndex (- (count tape) 1) 
          newIndex(+ currentIndex moveDirection)]
      (cond (= newIndex -1) 
            (vector nextState (prependBlank (replaceItemCol currentIndex tape newChar)) 0)
            (> newIndex maxIndex)
            (vector nextState (appendBlank (replaceItemCol currentIndex tape newChar)) newIndex) 
            :else 
            (vector nextState (replaceItemCol currentIndex tape newChar) newIndex))))

(defn moveToNext [state moves] 
  (println state)
  (let [nextMove (getNextMove state moves)]
    (cond nextMove
       (updateState state nextMove)
    :else 
         state )))

(defn moveCon [state moves] 
  (fn [state] 
    (moveToNext state moves)))

(def mc (moveCon s m)) 

(defn haltTest [coll]
  (let [step (fn step [xs seen]
              (lazy-seq
                ((fn [[f :as xs] seen]
                  (when-let [s (seq xs)]
                    (if (= seen f) 
                      '(true)
                        (step (rest s) f))))
                 xs seen)))]
  (cond (= true (first(step coll '()))) true :else false)))

(haltTest (iterate mc s)) 