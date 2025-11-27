# Quiz Analysis - Data Structures and SQL

## Question 1: Binary Tree Leaf Nodes

**Question:** A binary tree has n leaf nodes. The number of nodes with degree 2 in a full binary tree is:

**Options:**
- a. n + 1
- b. 2n – 1
- c. n – 1 ✓
- d. n

**Correct Answer: c. n – 1**

**Reasoning:**
- **Why c is correct:** In a full binary tree, every internal node has exactly 2 children. There's a mathematical relationship: if L = number of leaf nodes and I = number of internal nodes (degree 2), then L = I + 1. Therefore, I = L - 1. With n leaf nodes, nodes with degree 2 = n - 1.
- **Why others are wrong:**
  - a. n + 1: This reverses the relationship
  - b. 2n – 1: This represents the total number of nodes in a full binary tree, not just degree-2 nodes
  - d. n: Off by one from the correct formula

---

## Question 2: SQL WHERE vs HAVING

**Question:** A binary tree node structure is defined as: struct Node {int data; struct Node *left, *right;}; Which of the following is true?

**Note:** The question text doesn't match the answers - answers are about SQL WHERE/HAVING

**Options:**
- a. This query will produce an error since both cannot be used together.
- b. HAVING filters groups after aggregation. ✓
- c. WHERE and HAVING are interchangeable.
- d. HAVING filters rows before grouping.

**Correct Answer: b. HAVING filters groups after aggregation.**

**Reasoning:**
- **Why b is correct:** HAVING clause is specifically designed to filter groups AFTER the GROUP BY operation and aggregation functions have been applied.
- **Why others are wrong:**
  - a. False - WHERE and HAVING can be used together in the same query
  - c. False - They serve different purposes: WHERE filters rows before grouping, HAVING filters groups after aggregation
  - d. False - This describes WHERE, not HAVING

---

## Question 3: Circular Queue Operations

**Question:** A circular queue of size 5 currently contains elements [10, 20, 30, 40] with front = 0 and rear = 3. After two dequeue operations and one enqueue(50), what will be the new values of front and rear (0-based indexing)?

**Options:**
- a. front = 1, rear = 0
- b. front = 2, rear = 0
- c. front = 2, rear = 4 ✓
- d. front = 3, rear = 1

**Correct Answer: c. front = 2, rear = 4**

**Reasoning:**
- Initial state: front = 0, rear = 3, elements at positions [0,1,2,3] = [10,20,30,40]
- After 1st dequeue: front = 1 (removed 10)
- After 2nd dequeue: front = 2 (removed 20)
- After enqueue(50): rear = (3+1) % 5 = 4, element 50 inserted at position 4
- Final: front = 2, rear = 4
- **Why others are wrong:** They don't correctly follow circular queue pointer arithmetic

---

## Question 4: Database Denormalization

**Question:** A database designer decides to denormalize part of a schema. What could be the most likely reason for this decision?

**Options:**
- a. To enforce stronger foreign key constraints
- b. To reduce data redundancy
- c. To increase referential integrity
- d. To improve query performance by reducing joins ✓

**Correct Answer: d. To improve query performance by reducing joins**

**Reasoning:**
- **Why d is correct:** Denormalization intentionally introduces redundancy to reduce the need for expensive JOIN operations, thereby improving read performance. This is a common optimization for read-heavy systems.
- **Why others are wrong:**
  - a. Denormalization doesn't strengthen FK constraints
  - b. False - denormalization INCREASES redundancy, not reduces it
  - c. Denormalization typically weakens referential integrity

---

## Question 5: Index for Email Search

**Question:** A table Employees has 10 million rows. Which of the following will improve query performance for searching an employee by email (unique field)?

**Options:**
- a. Creating a non-clustered index on email ✓
- b. Storing all emails in uppercase format
- c. Creating a clustered index on email
- d. Using ORDER BY email in all queries

**Correct Answer: a. Creating a non-clustered index on email**

**Reasoning:**
- **Why a is correct:** A non-clustered index on the unique email field will create a B-tree structure allowing O(log n) lookups instead of O(n) table scans. Since email is unique and likely not the primary key, a non-clustered index is appropriate.
- **Why others are wrong:**
  - b. Case conversion doesn't improve search performance significantly
  - c. Only one clustered index per table (usually on primary key); changing it could harm other queries
  - d. ORDER BY doesn't improve search performance, only sorting of results

---

## Question 6: SQL String Functions

**Question:** CONCAT, UPPER, LOWER, and SUBSTRING are used together as follows:
SELECT CONCAT(UPPER(LEFT('servicenow', 1)), LOWER(SUBSTRING('SERVICENOW', 2)));
What will be the output?

**Options:**
- a. SERVICE
- b. ServicenOW
- c. SERVICENOW
- d. Servicenow ✓

**Correct Answer: d. Servicenow**

**Reasoning:**
- LEFT('servicenow', 1) = 's'
- UPPER('s') = 'S'
- SUBSTRING('SERVICENOW', 2) = 'ERVICENOW'
- LOWER('ERVICENOW') = 'ervicenow'
- CONCAT('S', 'ervicenow') = 'Servicenow'
- **Why others are wrong:** They don't follow the correct function execution order

---

## Question 7: Customers Without Orders

**Question:** Consider two tables: Orders(order_id, customer_id, order_date) and Customers(customer_id, customer_name). Which of the following queries retrieves customers who have not placed any orders?

**Options:**
- a. SELECT customer_name FROM Customers WHERE customer_id NOT IN (SELECT customer_id FROM Orders); ✓
- b. SELECT customer_name FROM Customers NATURAL JOIN Orders;
- c. SELECT customer_name FROM Customers WHERE customer_id IN (SELECT customer_id FROM Orders);
- d. SELECT customer_name FROM Customers WHERE EXISTS (SELECT * FROM Orders WHERE Customers.customer_id = Orders.customer_id);

**Correct Answer: a. SELECT customer_name FROM Customers WHERE customer_id NOT IN (SELECT customer_id FROM Orders);**

**Reasoning:**
- **Why a is correct:** NOT IN with a subquery finds customers whose IDs are not present in the Orders table, which are exactly those who haven't placed orders.
- **Why others are wrong:**
  - b. NATURAL JOIN returns only matching customers (those WITH orders)
  - c. IN returns customers WITH orders (opposite of requirement)
  - d. EXISTS returns customers WITH orders (opposite of requirement)

---

## Question 8: BFS Time Complexity

**Question:** For a graph with V vertices and E edges, what is the time complexity of Breadth-First Search (BFS) using an adjacency list representation?

**Options:**
- a. O(V + E) ✓
- b. O(VE)
- c. O(log V)
- d. O(V²)

**Correct Answer: a. O(V + E)**

**Reasoning:**
- **Why a is correct:** BFS visits each vertex once (O(V)) and examines each edge once (O(E)), giving O(V + E) total time complexity with adjacency list representation.
- **Why others are wrong:**
  - b. O(VE): Too high, this would mean checking every edge for every vertex
  - c. O(log V): Too low, not a divide-and-conquer algorithm
  - d. O(V²): This is the complexity with adjacency matrix, not adjacency list

---

## Question 9: Tree Traversal Without Recursion

**Question:** Given the following SQL command: [SQL query shown]
Which traversal order can be used to create a copy of the tree without using recursion?

**Note:** Question text doesn't match - it shows SQL but asks about tree traversal

**Options:**
- a. Preorder using a stack
- b. Inorder using a stack
- c. Level-order using a queue ✓
- d. Postorder using a queue

**Correct Answer: c. Level-order using a queue**

**Reasoning:**
- **Why c is correct:** Level-order traversal using a queue is the most straightforward non-recursive method to create a complete copy of a tree. It processes nodes level by level, naturally building the tree structure.
- **Why others are wrong:**
  - a. Preorder with stack works but is more complex for tree copying
  - b. Inorder doesn't preserve parent-child relationships well for copying
  - d. Postorder doesn't typically use a queue

---

## Question 10: Third Normal Form (3NF)

**Question:** If a table is in Third Normal Form (3NF), which of the following must be true?

**Options:**
- a. It has no partial dependency and no transitive dependency. ✓
- b. All attributes are atomic, but partial dependencies may exist.
- c. It can still contain redundant data.
- d. Each non-key attribute depends on a part of the composite key.

**Correct Answer: a. It has no partial dependency and no transitive dependency.**

**Reasoning:**
- **Why a is correct:** 3NF requires: (1) It's in 2NF (no partial dependencies), and (2) No transitive dependencies exist. This means every non-key attribute depends directly on the primary key, not on other non-key attributes.
- **Why others are wrong:**
  - b. Partial dependencies would violate 2NF, and 3NF must be in 2NF
  - c. 3NF specifically aims to eliminate redundancy from transitive dependencies
  - d. This describes a violation of 2NF, not compliance with 3NF

---

## Question 11: Topological Sorting in DAG

**Question:** In a directed acyclic graph (DAG), topological sorting is possible only if:

**Options:**
- a. The graph contains no cycles. ✓
- b. Every vertex has equal indegree and outdegree.
- c. The graph is connected.
- d. The graph has no self-loops.

**Correct Answer: a. The graph contains no cycles.**

**Reasoning:**
- **Why a is correct:** Topological sorting requires a DAG (Directed Acyclic Graph). The "acyclic" part is the key requirement - no cycles means we can establish a linear ordering where all dependencies are satisfied.
- **Why others are wrong:**
  - b. False - vertices can have different indegrees and outdegrees
  - c. False - disconnected DAGs can still be topologically sorted
  - d. Self-loops are just a special case of cycles; the broader requirement is no cycles at all

---

## Question 12: Queue Operations Complexity

**Question:** In a queue implemented using linked list, which operation is O(1)?

**Options:**
- a. Traversing the entire queue
- b. Deleting from front
- c. Inserting at rear
- d. Both B and C ✓

**Correct Answer: d. Both B and C**

**Reasoning:**
- **Why d is correct:** In a linked list queue with both front and rear pointers:
  - Deleting from front: O(1) - just update front pointer
  - Inserting at rear: O(1) - just update rear pointer and links
- **Why others are wrong:**
  - a. Traversing entire queue is O(n), not O(1)

---

## Question 13: ACID Property for Transaction Isolation

**Question:** In a relational database, which of the following ensures that changes made by one transaction are not visible to other transactions until committed?

**Options:**
- a. Atomicity
- b. Durability
- c. Isolation ✓
- d. Consistency

**Correct Answer: c. Isolation**

**Reasoning:**
- **Why c is correct:** Isolation ensures that concurrent transactions execute independently without interfering with each other. Uncommitted changes remain invisible to other transactions.
- **Why others are wrong:**
  - a. Atomicity: All-or-nothing execution
  - b. Durability: Committed changes persist after crashes
  - d. Consistency: Database moves from one valid state to another

---

## Question 14: Deleting Last Node in Singly Linked List

**Question:** In a singly linked list, deleting the last node requires traversal of the entire list. What is the time complexity of this operation if the list has n nodes?

**Options:**
- a. O(n) ✓
- b. O(n²)
- c. O(log n)
- d. O(1)

**Correct Answer: a. O(n)**

**Reasoning:**
- **Why a is correct:** To delete the last node in a singly linked list, you must traverse from head to the second-to-last node (to update its next pointer to NULL). This requires visiting n-1 nodes, which is O(n).
- **Why others are wrong:**
  - b. O(n²): Would require nested iteration
  - c. O(log n): No binary search structure exists
  - d. O(1): Impossible without a previous pointer in singly linked list

---

## Question 15: INNER JOIN vs LEFT JOIN

**Question:** In SQL, which of the following statements about INNER JOIN and LEFT JOIN is correct?

**Options:**
- a. LEFT JOIN performs faster than INNER JOIN always.
- b. INNER JOIN returns only matching rows, while LEFT JOIN returns all rows from the left table, even if there are no matches. ✓
- c. Both return identical results in all cases.
- d. LEFT JOIN returns only matching rows, while INNER JOIN returns all rows from both tables.

**Correct Answer: b. INNER JOIN returns only matching rows, while LEFT JOIN returns all rows from the left table, even if there are no matches.**

**Reasoning:**
- **Why b is correct:** This accurately describes the fundamental difference:
  - INNER JOIN: Only rows with matches in both tables
  - LEFT JOIN: All rows from left table + matching rows from right table (NULLs where no match)
- **Why others are wrong:**
  - a. Performance depends on data and indexes, not inherently faster
  - c. They return different results when there are non-matching rows
  - d. This reverses the definitions

---

## Question 16: ACID Property for Consistency After Failure

**Question:** The ACID property that ensures a transaction leaves the database in a consistent state — even after system failure — is:

**Options:**
- a. Durability ✓
- b. Isolation
- c. Atomicity
- d. Consistency

**Correct Answer: a. Durability**

**Reasoning:**
- **Why a is correct:** Durability ensures that once a transaction is committed, its changes persist even in the event of system failures (crashes, power loss). This is what maintains consistency "after system failure."
- **Why others are wrong:**
  - b. Isolation: Deals with concurrent transactions
  - c. Atomicity: All-or-nothing execution
  - d. Consistency: Ensures valid state transitions but doesn't specifically address post-failure persistence

---

## Question 17: Transaction Concurrency Issue

**Question:** Two transactions T1 and T2 are running concurrently. T1 reads data that T2 later updates, but T1 doesn't re-read it. This is an example of:

**Options:**
- a. Dirty Read
- b. Non-repeatable Read ✓
- c. Lost Update
- d. Phantom Read

**Correct Answer: b. Non-repeatable Read**

**Reasoning:**
- **Why b is correct:** Non-repeatable read occurs when a transaction reads the same data twice and gets different values because another transaction modified it in between. T1 reads data, T2 updates it, and if T1 re-reads, it would see different values.
- **Why others are wrong:**
  - a. Dirty Read: Reading uncommitted data
  - c. Lost Update: One transaction's update overwrites another's
  - d. Phantom Read: New rows appearing in range queries

---

## Question 18: SQL LOCATE Function

**Question:** What will be the output of the following query?
SELECT LOCATE('ice', 'ServiceNow');

**Options:**
- a. 0
- b. 5
- c. 4
- d. 6 ✓

**Correct Answer: d. 6**

**Reasoning:**
- LOCATE() returns the position of the first occurrence of substring (1-based indexing)
- 'ServiceNow': S-e-r-v-i-c-e-N-o-w
- Position:     1-2-3-4-5-6-7-8-9-10
- 'ice' starts at position 6 (i=6, c=7, e=8)
- **Why others are wrong:**
  - a. 0 would mean not found
  - b. 5 is where 'i' is, but we need the start of 'ice'
  - c. 4 is incorrect counting

---

## Question 19: SQL DATE_FORMAT

**Question:** What will be the result of the following query?
SELECT DATE_FORMAT('2025-11-10', '%d %M %Y');

**Options:**
- a. 10 November 2025 ✓
- b. 10-11-2025
- c. November 10 2025
- d. 2025/11/10

**Correct Answer: a. 10 November 2025**

**Reasoning:**
- %d = day of month (10)
- %M = full month name (November)
- %Y = 4-digit year (2025)
- Format string '%d %M %Y' produces: "10 November 2025"
- **Why others are wrong:** They don't match the format specifiers used

---

## Question 20: MySQL Character Count Function

**Question:** Which of the following MySQL functions returns the number of characters (not bytes) in a string?

**Options:**
- a. LENGTH()
- b. INSTR()
- c. CHAR_LENGTH() ✓
- d. STRCMP()

**Correct Answer: c. CHAR_LENGTH()**

**Reasoning:**
- **Why c is correct:** CHAR_LENGTH() (or CHARACTER_LENGTH()) returns the number of characters, regardless of character encoding. For multi-byte characters, this differs from byte count.
- **Why others are wrong:**
  - a. LENGTH() returns byte count, not character count
  - b. INSTR() finds substring position, not length
  - d. STRCMP() compares two strings

---

## Question 21: Calculate Age from Date of Birth

**Question:** Which of the following queries will correctly calculate the age of a student in years based on column dob?

**Options:**
- a. SELECT AGE(CURDATE(), dob) AS age FROM students;
- b. SELECT YEAR(CURDATE()) - YEAR(dob) AS age FROM students;
- c. SELECT DATEDIFF(CURDATE(), dob) / 365 AS age FROM students;
- d. SELECT TIMESTAMPDIFF(YEAR, dob, CURDATE()) AS age FROM students; ✓

**Correct Answer: d. SELECT TIMESTAMPDIFF(YEAR, dob, CURDATE()) AS age FROM students;**

**Reasoning:**
- **Why d is correct:** TIMESTAMPDIFF(YEAR, start, end) correctly calculates the number of complete years between two dates, accounting for leap years and exact dates.
- **Why others are wrong:**
  - a. AGE() is PostgreSQL syntax, not MySQL
  - b. Inaccurate - doesn't account for month/day (someone born Dec 31 and today is Jan 1 would show 1 year)
  - c. Division by 365 is imprecise (leap years, not exact)

---

## Question 22: Doubly Linked Lists

**Question:** Which of the following statements about doubly linked lists is true?

**Options:**
- a. They require more memory per node than singly linked lists. ✓
- b. Deletion of a given node requires traversal from the head node.
- c. They have faster search operations than singly linked lists.
- d. They cannot be traversed backwards.

**Correct Answer: a. They require more memory per node than singly linked lists.**

**Reasoning:**
- **Why a is correct:** Doubly linked lists have two pointers per node (next and prev) vs. one pointer in singly linked lists, requiring more memory.
- **Why others are wrong:**
  - b. False - deletion can be done in O(1) if you have the node reference
  - c. False - search is still O(n) for both; no inherent search advantage
  - d. False - backward traversal is the key advantage of doubly linked lists

---

## Question 23: Indexing Statements

**Question:** Which of the following statements about indexing is FALSE?

**Options:**
- a. A primary key automatically creates a clustered index in most relational databases.
- b. Indexes can be created on views to improve join performance. ✓
- c. Indexes improve query performance but may slow down INSERT and UPDATE operations.
- d. Composite indexes can speed up queries that filter by multiple columns.

**Correct Answer: b. Indexes can be created on views to improve join performance.**

**Reasoning:**
- **Why b is FALSE:** This is misleading. While some databases support indexed views (materialized views), regular views don't store data and can't have traditional indexes. The statement is too general and incorrect for most RDBMS contexts.
- **Why others are TRUE:**
  - a. True - primary keys typically create clustered indexes
  - c. True - indexes speed reads but slow writes
  - d. True - composite indexes help multi-column queries

---

## Question 24: BST In-Order Traversal

**Question:** Which traversal of a binary tree gives nodes in ascending order if the tree is a Binary Search Tree (BST)?

**Options:**
- a. Level Order
- b. Preorder
- c. Inorder ✓
- d. Postorder

**Correct Answer: c. Inorder**

**Reasoning:**
- **Why c is correct:** In-order traversal of a BST (left-root-right) visits nodes in sorted ascending order due to the BST property (left < root < right).
- **Why others are wrong:**
  - a. Level Order: Visits by level, not sorted order
  - b. Preorder: Root-left-right, not sorted
  - d. Postorder: Left-right-root, not sorted

---

## Question 25: Merging Two Sorted Linked Lists

**Question:** You are given two sorted singly linked lists. What is the best possible time complexity to merge them into one sorted linked list?

**Options:**
- a. O(log n + log m)
- b. O(n log n)
- c. O(n²)
- d. O(n + m) ✓

**Correct Answer: d. O(n + m)**

**Reasoning:**
- **Why d is correct:** Merging two sorted lists requires visiting each node exactly once. With n nodes in first list and m in second, total operations = n + m, giving O(n + m) complexity.
- **Why others are wrong:**
  - a. O(log n + log m): Too optimistic; we must process all nodes
  - b. O(n log n): This is sorting complexity, but lists are already sorted
  - c. O(n²): Too pessimistic; no nested iteration needed

---

## Summary of Correct Answers

1. **c** - n – 1
2. **b** - HAVING filters groups after aggregation
3. **c** - front = 2, rear = 4
4. **d** - To improve query performance by reducing joins
5. **a** - Creating a non-clustered index on email
6. **d** - Servicenow
7. **a** - NOT IN subquery
8. **a** - O(V + E)
9. **c** - Level-order using a queue
10. **a** - No partial dependency and no transitive dependency
11. **a** - The graph contains no cycles
12. **d** - Both B and C (enqueue and dequeue are O(1))
13. **c** - Isolation
14. **a** - O(n)
15. **b** - INNER JOIN only matching, LEFT JOIN all from left
16. **a** - Durability
17. **b** - Non-repeatable Read
18. **d** - 6
19. **a** - 10 November 2025
20. **c** - CHAR_LENGTH()
21. **d** - TIMESTAMPDIFF(YEAR, dob, CURDATE())
22. **a** - Require more memory per node
23. **b** - Indexes on views (FALSE statement)
24. **c** - Inorder
25. **d** - O(n + m)
