# Data Structures & Algorithms MCQs Study Guide

## Question 1
**A circular queue helps prevent:**

| Option | Answer |
|--------|--------|
| a. | Underflow |
| b. | Double deletion |
| **c.** | **Memory wastage ✓** |
| d. | Overflow |

### Concept:
In a linear queue, once elements are dequeued, the front spaces become unusable even if empty (front pointer moves forward). Circular queue:
- Wraps around using modulo: `rear = (rear + 1) % size`
- Reuses empty spaces at the front
- Prevents **memory wastage** by utilizing all array positions

### Why others are wrong:
- **a. Underflow**: Occurs when dequeue from empty queue; circular queue doesn't prevent this
- **b. Double deletion**: A programming error, not solved by circular structure
- **d. Overflow**: Still occurs when queue is full; circular queue just uses space efficiently

---

## Question 2
**A perfect binary tree of height h has how many nodes?**

| Option | Answer |
|--------|--------|
| a. | 2^h |
| b. | h |
| **c.** | **2^(h+1) - 1 ✓** |
| d. | h² |

### Concept:
A perfect binary tree has all levels completely filled:
- Level 0: 1 node (2⁰)
- Level 1: 2 nodes (2¹)
- Level h: 2^h nodes

Total = 2⁰ + 2¹ + ... + 2^h = **2^(h+1) - 1** (geometric series sum)

Example: h=2 → 1 + 2 + 4 = 7 = 2³ - 1

### Why others are wrong:
- **a. 2^h**: Only counts nodes at the last level
- **b. h**: Just the height value, way too small
- **d. h²**: Quadratic formula, doesn't apply to tree structure

---

## Question 3
**A queue works on which principle?**

| Option | Answer |
|--------|--------|
| a. | LIFO |
| b. | Random |
| **c.** | **FIFO ✓** |
| d. | FILO |

### Concept:
Queue follows **FIFO (First In, First Out)**:
- Elements added at rear (enqueue)
- Elements removed from front (dequeue)
- Like a line at a ticket counter: first person in line gets served first

### Why others are wrong:
- **a. LIFO**: Last In First Out - used by Stack
- **b. Random**: No data structure uses random access for primary operations
- **d. FILO**: First In Last Out - same as LIFO, used by Stack

---

## Question 4
**In a graph, BFS is preferred over DFS when we need:**

| Option | Answer |
|--------|--------|
| **a.** | **Shortest path in unweighted graph ✓** |
| b. | To use less memory |
| c. | To detect cycles faster |
| d. | Deepest node first |

### Concept:
BFS explores level by level (using a queue):
- First visits all nodes at distance 1, then distance 2, etc.
- Guarantees shortest path (minimum edges) in unweighted graphs
- When target found, path length equals the level number

### Why others are wrong:
- **b. To use less memory**: BFS uses O(V) memory for queue; DFS uses O(h) for stack - DFS often uses less
- **c. To detect cycles faster**: Both can detect cycles; no significant speed difference
- **d. Deepest node first**: That's DFS behavior, not BFS

---

## Question 5
**In a hash table, collisions are handled using:**

| Option | Answer |
|--------|--------|
| **a.** | **Chaining ✓** |
| b. | Sorting |
| c. | Binary Search |
| d. | DFS |

### Concept:
Collision occurs when two keys hash to the same index. **Chaining**:
- Each bucket contains a linked list
- Colliding elements stored in the same list
- Other methods: Open Addressing (Linear Probing, Quadratic Probing, Double Hashing)

### Why others are wrong:
- **b. Sorting**: Doesn't resolve collisions; sorting is for ordering elements
- **c. Binary Search**: Search algorithm, not collision resolution
- **d. DFS**: Graph traversal algorithm, unrelated to hashing

---

## Question 6
**In a max heap, the largest element is stored at:**

| Option | Answer |
|--------|--------|
| a. | Leftmost node |
| **b.** | **Root ✓** |
| c. | Middle node |
| d. | Leaf node |

### Concept:
Max Heap property: Every parent node ≥ its children
- Largest element is always at the **root**
- Enables O(1) access to maximum
- Used in priority queues, heap sort
- Insertion and deletion: O(log n)

### Why others are wrong:
- **a. Leftmost node**: Position doesn't determine value in heap
- **c. Middle node**: No special property for middle nodes
- **d. Leaf node**: Leaves contain smaller elements (no children to be larger than)

---

## Question 7
**In a singly linked list, which operation is the fastest?**

| Option | Answer |
|--------|--------|
| a. | Delete last node |
| b. | Search a node |
| **c.** | **Insert at beginning ✓** |
| d. | Insert at end |

### Concept:
**Insert at beginning** is O(1):
1. Create new node
2. Point new node's next to current head
3. Update head to new node

No traversal required!

### Why others are wrong:
- **a. Delete last node**: O(n) - must traverse to find second-to-last node
- **b. Search a node**: O(n) - may need to traverse entire list
- **d. Insert at end**: O(n) - must traverse to find last node (unless tail pointer maintained)

---

## Question 8
**The maximum number of nodes at level k in a binary tree is:**

| Option | Answer |
|--------|--------|
| **a.** | **2^k ✓** |
| b. | 2^(k-1) |
| c. | k² |
| d. | k |

### Concept:
In a binary tree (root at level 0):
- Level 0: max 1 node (2⁰)
- Level 1: max 2 nodes (2¹)
- Level 2: max 4 nodes (2²)
- **Level k: max 2^k nodes**

Each level doubles the maximum possible nodes.

### Why others are wrong:
- **b. 2^(k-1)**: Off by one; would be correct if root is at level 1
- **c. k²**: Quadratic growth doesn't match binary tree structure
- **d. k**: Linear growth, too slow for tree structure

---

## Question 9
**The minimum number of edges in a connected graph with n nodes is:**

| Option | Answer |
|--------|--------|
| a. | 2n |
| **b.** | **n – 1 ✓** |
| c. | n + 1 |
| d. | n |

### Concept:
A connected graph with minimum edges forms a **tree**:
- n nodes connected with exactly **n - 1** edges
- Any fewer edges → graph becomes disconnected
- Adding any edge → creates a cycle

This is the fundamental property of trees.

### Why others are wrong:
- **a. 2n**: Too many; would create cycles
- **c. n + 1**: Creates exactly one cycle
- **d. n**: Creates exactly one cycle

---

## Question 10
**What is the auxiliary space of Merge Sort?**

| Option | Answer |
|--------|--------|
| a. | O(log n) |
| **b.** | **O(n) ✓** |
| c. | O(1) |
| d. | O(n²) |

### Concept:
Merge Sort requires **O(n)** auxiliary space:
- Needs temporary arrays for merging
- During merge, copies elements to temp array
- Total extra space equals input size

Trade-off: Stable sort, O(n log n) time, but not in-place.

### Why others are wrong:
- **a. O(log n)**: Only accounts for recursion stack, not merge space
- **c. O(1)**: In-place algorithms like Heap Sort, not Merge Sort
- **d. O(n²)**: Excessive; no standard sorting algorithm needs this

---

## Question 11
**What is the time complexity of enqueue in a queue?**

| Option | Answer |
|--------|--------|
| a. | O(log n) |
| **b.** | **O(1) ✓** |
| c. | O(n²) |
| d. | O(n) |

### Concept:
Enqueue is **O(1)** - constant time:
- Array implementation: Insert at rear index, increment rear
- Linked list: Add node at tail (with tail pointer)
- No traversal or searching required

### Why others are wrong:
- **a. O(log n)**: Would require some form of searching/balancing
- **c. O(n²)**: Far too slow for a basic operation
- **d. O(n)**: Would require traversal, not needed for enqueue

---

## Question 12
**What is the time complexity of inserting a node in a BST (average case)?**

| Option | Answer |
|--------|--------|
| a. | O(n log n) |
| **b.** | **O(log n) ✓** |
| c. | O(1) |
| d. | O(n) |

### Concept:
BST insertion (average case) is **O(log n)**:
- Compare with root, go left or right
- Each comparison eliminates half the remaining nodes
- Height of balanced BST ≈ log n

Worst case (skewed tree): O(n)

### Why others are wrong:
- **a. O(n log n)**: Time for sorting n elements, not single insertion
- **c. O(1)**: Would need direct access without traversal
- **d. O(n)**: Worst case for skewed tree, not average

---

## Question 13
**What is the time complexity of linear search in the worst case?**

| Option | Answer |
|--------|--------|
| **a.** | **O(n) ✓** |
| b. | O(log n) |
| c. | O(1) |
| d. | O(n²) |

### Concept:
Linear search checks each element sequentially:
- Best case: O(1) - element at first position
- Average case: O(n/2) → O(n)
- **Worst case: O(n)** - element at last position or not present

Must check all n elements in worst case.

### Why others are wrong:
- **b. O(log n)**: Binary search complexity, not linear search
- **c. O(1)**: Only if element is at first position (best case)
- **d. O(n²)**: Linear search doesn't have nested operations

---

## Question 14
**What is the worst-case height of a Red-Black Tree?**

| Option | Answer |
|--------|--------|
| a. | O(1) |
| **b.** | **O(log n) ✓** |
| c. | O(n²) |
| d. | O(n) |

### Concept:
Red-Black Tree guarantees **O(log n)** height:
- Self-balancing BST with color properties
- Height ≤ 2 log₂(n + 1)
- Properties ensure no path is more than twice as long as any other

All operations (insert, delete, search): O(log n)

### Why others are wrong:
- **a. O(1)**: Trees grow with elements; can't be constant height
- **c. O(n²)**: No tree structure has quadratic height
- **d. O(n)**: That's unbalanced BST; Red-Black prevents this

---

## Question 15
**What is the worst-case time complexity of Bubble Sort?**

| Option | Answer |
|--------|--------|
| a. | O(n) |
| b. | O(n log n) |
| c. | O(log n) |
| **d.** | **O(n²) ✓** |

### Concept:
Bubble Sort worst case is **O(n²)**:
- Outer loop: n iterations
- Inner loop: n-1, n-2, ... , 1 comparisons
- Total: n(n-1)/2 = O(n²)
- Worst case: reverse sorted array

### Why others are wrong:
- **a. O(n)**: Best case with optimization (already sorted)
- **b. O(n log n)**: Efficient algorithms like Merge Sort, Quick Sort
- **c. O(log n)**: Binary search, not sorting

---

## Question 16
**What structure is best for implementing undo operation?**

| Option | Answer |
|--------|--------|
| a. | HashMap |
| b. | Array |
| c. | Queue |
| **d.** | **Stack ✓** |

### Concept:
**Stack** is ideal for undo:
- LIFO: Last action performed should be first to undo
- Push each action onto stack
- Pop to undo most recent action
- Used in text editors, browsers, IDEs

### Why others are wrong:
- **a. HashMap**: Key-value storage, no ordering for sequential undo
- **b. Array**: Doesn't inherently support LIFO behavior efficiently
- **c. Queue**: FIFO - would undo oldest action first, not most recent

---

## Question 17
**Which algorithm detects negative weight cycles?**

| Option | Answer |
|--------|--------|
| **a.** | **Bellman–Ford ✓** |
| b. | BFS |
| c. | Dijkstra |
| d. | Floyd–Warshall |

### Concept:
**Bellman-Ford** detects negative weight cycles:
- Relaxes all edges V-1 times
- If any edge can still be relaxed on V-th iteration → negative cycle exists
- Time: O(VE)

Note: Floyd-Warshall can also detect (via negative diagonal), but Bellman-Ford is the primary algorithm for this.

### Why others are wrong:
- **b. BFS**: Unweighted shortest path, doesn't consider edge weights
- **c. Dijkstra**: Fails with negative weights entirely; doesn't detect cycles
- **d. Floyd–Warshall**: Can detect but not the primary purpose; Bellman-Ford is standard answer

---

## Question 18
**Which algorithm is used to detect cycles in an undirected graph?**

| Option | Answer |
|--------|--------|
| a. | Kruskal |
| **b.** | **DFS ✓** |
| c. | Bellman-Ford |
| d. | BFS |

### Concept:
**DFS** detects cycles in undirected graphs:
- Track visited nodes and parent of each node
- If we visit an already-visited node that's NOT the parent → cycle found
- Time: O(V + E)

Union-Find can also detect cycles (used in Kruskal's).

### Why others are wrong:
- **a. Kruskal**: MST algorithm; uses Union-Find for cycle detection as a sub-step
- **c. Bellman-Ford**: Detects negative weight cycles, not general cycles in undirected graphs
- **d. BFS**: Can detect cycles but DFS is more commonly used and efficient for this

---

## Question 19
**Which algorithm uses a pivot?**

| Option | Answer |
|--------|--------|
| a. | Bubble Sort |
| **b.** | **Quick Sort ✓** |
| c. | Heap Sort |
| d. | Merge Sort |

### Concept:
**Quick Sort** uses a pivot element:
1. Select pivot (first, last, random, or median)
2. Partition: elements < pivot go left, > pivot go right
3. Recursively sort left and right partitions

Pivot selection affects performance significantly.

### Why others are wrong:
- **a. Bubble Sort**: Compares adjacent elements, no pivot
- **c. Heap Sort**: Uses heap property for sorting
- **d. Merge Sort**: Divides in half, no pivot selection

---

## Question 20
**Which data structure is used for implementing recursion?**

| Option | Answer |
|--------|--------|
| a. | Array |
| b. | Queue |
| **c.** | **Stack ✓** |
| d. | Linked List |

### Concept:
**Stack** (Call Stack) implements recursion:
- Each function call pushes a stack frame
- Contains: return address, local variables, parameters
- Function return pops the frame
- LIFO order matches recursive call/return pattern

### Why others are wrong:
- **a. Array**: Static storage, doesn't manage call frames
- **b. Queue**: FIFO doesn't match recursion's LIFO nature
- **d. Linked List**: General structure, not specifically for call management

---

## Question 21
**Which data structure is used in BFS?**

| Option | Answer |
|--------|--------|
| a. | Stack |
| **b.** | **Queue ✓** |
| c. | Priority Queue |
| d. | Deque |

### Concept:
**Queue** is used in BFS:
- FIFO ensures level-by-level exploration
- Enqueue neighbors, dequeue to visit
- Guarantees nodes at distance k are processed before distance k+1

BFS algorithm:
1. Enqueue start node
2. Dequeue, visit, enqueue unvisited neighbors
3. Repeat until queue empty

### Why others are wrong:
- **a. Stack**: Used in DFS, gives depth-first behavior
- **c. Priority Queue**: Used in Dijkstra's, A* for weighted graphs
- **d. Deque**: Can implement BFS but standard queue is simpler and sufficient

---

## Question 22
**Which of the following applications uses a priority queue?**

| Option | Answer |
|--------|--------|
| a. | BFS |
| **b.** | **Dijkstra's Algorithm ✓** |
| c. | DFS |
| d. | Binary Search |

### Concept:
**Dijkstra's Algorithm** uses a priority queue (min-heap):
- Always processes the vertex with smallest current distance
- Extract-min: O(log V)
- Decrease-key: O(log V)
- Total: O((V + E) log V)

Priority queue ensures greedy selection of minimum distance vertex.

### Why others are wrong:
- **a. BFS**: Uses regular queue (FIFO)
- **c. DFS**: Uses stack (LIFO)
- **d. Binary Search**: Uses array indices, no queue

---

## Question 23
**Which of the following is NOT a self-balancing BST?**

| Option | Answer |
|--------|--------|
| a. | Red-Black Tree |
| **b.** | **Binary Heap ✓** |
| c. | AVL Tree |
| d. | Splay Tree |

### Concept:
**Binary Heap** is NOT a BST:
- Heap property: Parent ≥ children (max) or Parent ≤ children (min)
- NOT a search tree: No left < root < right ordering
- Used for priority queues, not searching

### Why others are wrong:
- **a. Red-Black Tree**: Self-balancing BST with color rules
- **c. AVL Tree**: Self-balancing BST with height balance factor
- **d. Splay Tree**: Self-adjusting BST that moves accessed nodes to root

---

## Question 24
**Which operation is costly in a stack?**

| Option | Answer |
|--------|--------|
| a. | Pop |
| b. | Top |
| **c.** | **Access middle element ✓** |
| d. | Push |

### Concept:
**Accessing middle element** is O(n) in a stack:
- Stack only provides access to top element
- Must pop elements to reach middle
- Violates stack's LIFO principle

Stack is optimized for top operations only.

### Why others are wrong:
- **a. Pop**: O(1) - remove from top
- **b. Top**: O(1) - view top element
- **d. Push**: O(1) - add to top

---

## Question 25
**Which operation is costly in an array?**

| Option | Answer |
|--------|--------|
| a. | Update element |
| b. | Access element |
| **c.** | **Insert at beginning ✓** |
| d. | Access last element |

### Concept:
**Insert at beginning** is O(n):
- Must shift ALL existing elements one position right
- n elements = n shift operations
- Same for delete at beginning

### Why others are wrong:
- **a. Update element**: O(1) - direct access via index
- **b. Access element**: O(1) - array[index] is constant time
- **d. Access last element**: O(1) - array[length-1]

---

## Question 26
**Which searching algorithm requires a sorted array?**

| Option | Answer |
|--------|--------|
| a. | Linear Search |
| b. | Hash Search |
| c. | DFS |
| **d.** | **Binary Search ✓** |

### Concept:
**Binary Search** requires sorted array:
- Compares target with middle element
- Eliminates half of remaining elements each step
- Only works if array is ordered
- Time: O(log n)

### Why others are wrong:
- **a. Linear Search**: Works on any array, sorted or unsorted
- **b. Hash Search**: Uses hash function, doesn't need sorting
- **c. DFS**: Graph traversal, not array searching

---

## Question 27
**Which sorting algorithm has the best average-case complexity?**

| Option | Answer |
|--------|--------|
| **a.** | **Quick Sort ✓** |
| b. | Insertion Sort |
| c. | Bubble Sort |
| d. | Selection Sort |

### Concept:
**Quick Sort** average case: O(n log n)
- Divide and conquer with pivot
- In-place sorting (O(log n) space for recursion)
- Cache-efficient due to locality

Other O(n log n) algorithms: Merge Sort, Heap Sort

### Why others are wrong:
- **b. Insertion Sort**: O(n²) average case
- **c. Bubble Sort**: O(n²) average case
- **d. Selection Sort**: O(n²) average case

---

## Question 28
**Which sorting algorithm is stable?**

| Option | Answer |
|--------|--------|
| a. | Heap Sort |
| b. | Selection Sort |
| c. | Quick Sort |
| **d.** | **Merge Sort ✓** |

### Concept:
**Stable sort**: Maintains relative order of equal elements.

**Merge Sort** is stable:
- During merge, when elements are equal, takes from left array first
- Preserves original order of duplicates

### Why others are wrong:
- **a. Heap Sort**: Swaps can change relative order of equal elements
- **b. Selection Sort**: Swapping can disrupt order of equals
- **c. Quick Sort**: Partitioning can change relative order

---

## Question 29
**Which traversal prints the left subtree, root, then right subtree?**

| Option | Answer |
|--------|--------|
| **a.** | **Inorder ✓** |
| b. | Postorder |
| c. | Level order |
| d. | Preorder |

### Concept:
**Inorder traversal**: Left → Root → Right
- For BST: Produces sorted output
- Visits left subtree completely before root

Traversal orders:
- **Preorder**: Root → Left → Right
- **Postorder**: Left → Right → Root

### Why others are wrong:
- **b. Postorder**: Left → Right → Root
- **c. Level order**: Level by level, left to right (BFS)
- **d. Preorder**: Root → Left → Right

---

## Question 30
**Which tree traversal uses a queue?**

| Option | Answer |
|--------|--------|
| a. | Inorder |
| **b.** | **Level order ✓** |
| c. | Preorder |
| d. | Postorder |

### Concept:
**Level order (BFS)** uses a queue:
1. Enqueue root
2. Dequeue node, visit it
3. Enqueue left child, then right child
4. Repeat until queue empty

FIFO ensures level-by-level traversal.

### Why others are wrong:
- **a. Inorder**: Uses stack (recursion or explicit)
- **c. Preorder**: Uses stack (recursion or explicit)
- **d. Postorder**: Uses stack (recursion or explicit)

---

## Summary Table: Questions You Got Wrong

| Question | Topic | Correct Answer |
|----------|-------|----------------|
| Q8 | Binary Tree Levels | a. 2^k |
| Q18 | Cycle Detection | b. DFS |
| Q23 | Self-balancing BST | b. Binary Heap (is NOT a self-balancing BST) |

---

## Key Concepts Quick Reference

### Time Complexity Comparison

| Algorithm | Best | Average | Worst | Space |
|-----------|------|---------|-------|-------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) |

### Data Structure Operations

| Structure | Access | Search | Insert | Delete |
|-----------|--------|--------|--------|--------|
| Array | O(1) | O(n) | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1)* | O(1)* |
| Stack | O(n) | O(n) | O(1) | O(1) |
| Queue | O(n) | O(n) | O(1) | O(1) |
| Hash Table | N/A | O(1) avg | O(1) avg | O(1) avg |
| BST (balanced) | O(log n) | O(log n) | O(log n) | O(log n) |

*With pointer to position

### Tree Traversals

| Traversal | Order | Data Structure | Use Case |
|-----------|-------|----------------|----------|
| Preorder | Root → Left → Right | Stack | Copy tree, prefix expression |
| Inorder | Left → Root → Right | Stack | Sorted output from BST |
| Postorder | Left → Right → Root | Stack | Delete tree, postfix expression |
| Level Order | Level by level | Queue | BFS, shortest path in tree |

### Graph Algorithms

| Algorithm | Purpose | Data Structure | Time Complexity |
|-----------|---------|----------------|-----------------|
| BFS | Shortest path (unweighted) | Queue | O(V + E) |
| DFS | Traversal, cycle detection | Stack | O(V + E) |
| Dijkstra | Shortest path (weighted) | Priority Queue | O((V+E) log V) |
| Bellman-Ford | Shortest path, negative edges | Array | O(VE) |
| Kruskal | MST | Union-Find | O(E log E) |
| Prim | MST | Priority Queue | O((V+E) log V) |

### Binary Tree Formulas

| Property | Formula |
|----------|---------|
| Max nodes at level k | 2^k |
| Max nodes in tree of height h | 2^(h+1) - 1 |
| Min height for n nodes | ⌈log₂(n+1)⌉ - 1 |
| Leaves in perfect binary tree | 2^h |
| Internal nodes in perfect binary tree | 2^h - 1 |

### Self-Balancing Trees

| Tree Type | Balance Condition | Operations |
|-----------|-------------------|------------|
| AVL Tree | Height difference ≤ 1 | O(log n) |
| Red-Black Tree | Color properties | O(log n) |
| Splay Tree | Move accessed to root | O(log n) amortized |
| B-Tree | Multi-way balanced | O(log n) |

**Note**: Binary Heap is NOT a BST - it's a complete binary tree with heap property!
