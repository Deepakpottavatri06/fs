# JavaScript & React Quiz - Complete Answer Key

## JavaScript Questions

### Question 1
**Which is NOT a valid way to create an object?**  
✅ **c. `var obj = create Object();`**  
*(Invalid syntax. Correct ways: `Object()`, `new Object()`, `{}`)*  
Other options:  
a. Valid constructor call  
b. Valid constructor with `new`  
d. Valid object literal  

### Question 2
**Which hook stores mutable values without re-render?**  
✅ **c. `useRef`**  
*(Refs persist between renders without updates)*  
Other options:  
a. `useMemo` caches values  
b. `useState` triggers re-renders  
d. `useEffect` handles side effects  

### Question 3
**Which is NOT a JavaScript iterable?**  
✅ **a. Object**  
*(Plain objects need `Symbol.iterator`)*  
Other options:  
b. Arrays are iterable  
c. Strings are iterable  
d. Sets are iterable  

### Question 4
**What does `Object.freeze()` do?**  
✅ **b. Prevents property modification**  
*(Makes object immutable)*  
Other options:  
a. Doesn't affect inheritance  
c. Doesn't prevent copying  
d. Doesn't allow property changes  

### Question 5
**`useEffect` with no dependency array?**  
✅ **c. Runs on every render**  
*(No deps = runs after each render)*  
Other options:  
a. Cleanup runs on unmount  
b. Needs empty array to not run  
d. Empty array runs once  

### Question 6
**Why bind `this` in class components?**  
✅ **d. Maintain method context**  
*(Ensures `this` refers to component)*  
Other options:  
a. Not closure-related  
b. Not memoization  
c. Not Redux-specific  

### Question 7
**Copies object properties?**  
✅ **b. `Object.assign()`**  
*(Copies enumerable properties)*  
Other options:  
a/c/d. Not valid JS methods  

### Question 8
**Hook for memoizing computations?**  
✅ **a. `useMemo`**  
*(Caches expensive calculations)*  
Other options:  
b. `useCallback` memoizes functions  
c. `useEffect` for side effects  
d. `useReducer` for state  

### Question 9
**Handles loading with `React.lazy()`?**  
✅ **c. `React.Suspense`**  
*(Wrapper for lazy loading)*  
Other options:  
a. Fragments group elements  
b. Portals render elsewhere  
d. No such component  

### Question 10
**`typeof null` is?**  
✅ **a. "object"**  
*(Historical JavaScript quirk)*  
Other options:  
b. Would be logical but wrong  
c. `undefined` gives "undefined"  
d. Incorrect type  

### Question 11
**React reconciliation process?**  
✅ **d. Compares virtual DOM trees**  
*(Efficient DOM updates)*  
Other options:  
a. Not backend syncing  
b. Doesn't redraw entire page  
c. More than just changed nodes  

### Question 12
**React `key` prop purpose?**  
✅ **a. Unique component identifier**  
*(Helps React track elements)*  
Other options:  
b. Not for animations  
c. Not render timing  
d. Not for styling  

### Question 13
**JavaScript closures?**  
✅ **a. Retain creation scope access**  
*(Core closure behavior)*  
Other options:  
b. Work with any variables  
c. Can't access future vars  
d. Can access outer vars  

### Question 14
**`this` in strict mode functions?**  
✅ **b. `undefined`**  
*(Strict mode behavior)*  
Other options:  
a. Not function itself  
c. Not event object  
d. Not global object  

### Question 15
**Invalid lifecycle method?**  
✅ **c. `shouldComponentRender`**  
*(Correct is `shouldComponentUpdate`)*  
Other options:  
a/b/d. Valid lifecycle methods  

### Question 16
**`React.lazy()` purpose?**  
✅ **d. Code splitting**  
*(Load components dynamically)*  
Other options:  
a. Suspense handles rendering  
b. Not for events  
c. Not SSR-specific  

### Question 17
**"Lifting state up" means?**  
✅ **d. Move to common ancestor**  
*(Shared state management)*  
Other options:  
a. Partial (but not complete)  
b. Not global useState  
c. Not Redux-specific  

### Question 18
**`defaultProps` purpose?**  
✅ **d. Fallback prop values**  
*(Default when prop undefined)*  
Other options:  
a. Doesn't prevent mutation  
b. `propTypes` handles validation  
c. Not event listeners  

### Question 19
**Hook for side effects?**  
✅ **d. `useEffect`**  
*(Primary side effect hook)*  
Other options:  
a. `useMemo` for values  
b. `useReducer` for state  
c. `useContext` for context  

### Question 20
**Arrow functions?**  
✅ **c. Lexical `this` binding**  
*(Inherits `this` from scope)*  
Other options:  
a. Can't be constructors  
b. No `arguments` object  
d. Don't have own `this`  

### Question 21
**Batches state updates?**  
✅ **b. Concurrent Mode**  
*(Automatic batching feature)*  
Other options:  
a. Virtual DOM is different  
c. Fiber enables it but not directly  
d. Refs unrelated  

### Question 22
**Code output: `var a = b = 5`?**  
✅ **c. 5**  
*(`b` becomes global variable)*  
Other options:  
a. Not NaN  
b. Defined in global scope  
d. No ReferenceError  

### Question 23
**JavaScript async handling?**  
✅ **d. Event loop**  
*(Concurrency model)*  
Other options:  
a. No global queue  
b. Single-threaded  
c. No semaphores  

### Question 24
**`useImperativeHandle` purpose?**  
✅ **a. Customize ref exposure**  
*(Control parent-ref access)*  
Other options:  
b. Doesn't prevent rendering  
c. Not prop passing  
d. Not global hooks  

### Question 25
**`useCallback` returns?**  
✅ **c. Memoized callback**  
*(Same function reference)*  
Other options:  
a. `useMemo` returns values  
b. Effects are different  
d. Reducers are different  

### Question 26
**`setState` in `render()`?**  
✅ **c. Infinite loop**  
*(Render triggers setState triggers render)*  
Other options:  
a. Would keep re-rendering  
b. Does have effect  
d. Not syntax error  

### Question 27
**`memo` purpose?**  
✅ **c. Prevent unnecessary re-renders**  
*(Performance optimization)*  
Other options:  
a. Not event handling  
b. Not state management  
d. Not prop initialization  

### Question 28
**Context API goal?**  
✅ **d. Avoid prop drilling**  
*(Component tree state sharing)*  
Other options:  
a. Redux alternative but not replacement  
b. Not styling  
c. Not SSR-specific  

### Question 29
**`dangerouslySetInnerHTML`?**  
✅ **b. Render raw HTML**  
*(XSS risk - use carefully)*  
Other options:  
a. Not CSS  
c. Not headers  
d. Not JSX compilation  

### Question 30
**Strict Mode behavior?**  
✅ **c. Highlights problems**  
*(Development checks)*  
Other options:  
a. No optimizations  
b. Concurrent Mode enables concurrency  
d. Doesn't block updates  

## Answer Key Summary

| Q# | Correct | Concept |
|----|---------|---------|
| 1-10 | See above | JavaScript fundamentals |
| 11-20 | See above | React core concepts |
| 21-30 | See above | Advanced topics |

**Key Patterns:**
- ✅ Correct answers marked with green check
- 💡 Explanations for why other options are wrong
- 🗂️ Organized by question type
- 🔍 Clear technical distinctions