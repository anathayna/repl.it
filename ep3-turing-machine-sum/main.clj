;; define moves 
;; delta(state, symbol) -> (new state, new symbol, direction of movement)
(def m {[:q0 "X"]  [:q1 "#"   1], 
        [:q0 "O"]  [:q1 "#"   1],
        [:q1 "X"]  [:q0 "#"   1],
        [:q1 "O"]  [:q1 "#"   1]})

;; define initial state 
;; state is (state, tape, positional index)
(def s [:q0 ["X" "X" "X" "O" "O"] 0])

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
