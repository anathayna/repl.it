;; define moves 
;; delta(state, symbol) -> (new state, new symbol, direction of movement)
(def m {[:q0 1]  [:q0 1   1], 
        [:q0 0]  [:q1 1   1],
        [:q1 1]  [:q1 1   1],
        [:q1 :B] [:q2 :B -1],
        [:q2 1]  [:q3 0  -1],
        [:q3 1]  [:q3 1  -1],
        [:q3 :B] [:q4 :B  1]})

;; define initial state 
;; state is (state, tape, positional index)
(def s [:q0 [1 1 1 0 1 1] 0])

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