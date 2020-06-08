;; define moves 
;; delta(state, symbol) -> (new state, new symbol, direction of movement)
(def m {[:q0 "X"]  [:q1 :B    1], 
        [:q0 "O"]  [:q5 :B    1], 
        [:q0 :B ]  [:q9 :B    1],

        [:q1 "X"]  [:q1 "X"   1],
        [:q1 "O"]  [:q2 "O"   1],
        [:q1 "#"]  [:q4 "#"  -1],
        [:q2 "X"]  [:q2 "X"   1],
        [:q2 "O"]  [:q2 "O"   1],
        [:q2 "#"]  [:q2 "#"   1],
        [:q3 "X"]  [:q3 "X"  -1],
        [:q3 "O"]  [:q3 "O"  -1],
        [:q3 "#"]  [:q3 "#"  -1],
        [:q3 "A"]  [:q1 "A"   1],
        [:q4 "X"]  [:q4 "X"  -1],
        [:q4 "A"]  [:q4 "O"  -1],

        [:q5 "O"]  [:q5 "O"   1],
        [:q5 "X"]  [:q6 "A"   1],
        [:q5 "#"]  [:q8 "#"  -1],
        [:q6 "X"]  [:q6 "X"   1],
        [:q6 "O"]  [:q6 "O"   1],
        [:q6 "#"]  [:q6 "#"   1],
        [:q6 :B ]  [:q7 "#"  -1],
        [:q7 "X"]  [:q7 "X"  -1],
        [:q7 "O"]  [:q7 "O"  -1],
        [:q7 "#"]  [:q7 "#"  -1],
        [:q7 "A"]  [:q5 "A"   1],
        [:q8 "O"]  [:q8 "O"  -1],
        [:q8 "A"]  [:q8 "X"  -1]})

;; define initial state 
;; state is (state, tape, positional index)
(def s [:q0 ["X" "X" "O" "O"] 0])
;;(def s [:q0 ["X" "X" "O" "O" "X" "X" "X"] 0])

;; no next move, returns nil
;; fn(state, moves) -> (new state, changed char, move direction) 
(defn getNextMove[state moves] 
  (moves [(state 0) ((state 1)(state 2))]))

;; fn(index, col, new item) -> col 
;; needed to update tape for movement
(defn replaceItemCol [index col item]
  (let [n (count col)
        nthIndex (+ index 1)]
  (vec (concat (take index col) [item] (nthnext col nthIndex))))) 

;; fn(col) -> :B :: col 
(defn prependBlank [col] 
  (vec (cons :B col)))
;; fn(col) -> col :: :B 
(defn appendBlank [col]
  (vec (conj col :B)))

;;;; fn(state, production) -> state
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


;; (fn state, moves) -> state
;; halt state returns same state, w/o moving
(defn moveToNext [state moves] 
  (println state)
  (let [nextMove (getNextMove state moves)]
    (cond nextMove
       (updateState state nextMove)
    :else 
         state )))

;; Higher Order function to allow for iteration
;; fn(state, moves) -> ( fn(state) -> state ) 
(defn moveCon [state moves] 
  (fn [state] 
    (moveToNext state moves)))

;; mc is movecon created from m & s as defined in this file 
(def mc (moveCon s m)) 


;; takes a collection of states and determines if the turing machine has
;; halted on a given state, (state(-1) == state(0), for two states) 
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

;; Run the turing machine test, if it doesn't halt, there won't be a return 
(haltTest (iterate mc s))
;; A little bit safer, in the even that there is no halt an infinite 
;; loop will be avoided 
(haltTest (take 1000 (iterate mc s))) 
