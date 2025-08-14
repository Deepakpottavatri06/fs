/*You’re building a VIP Shuttle Bay controller:
VIP shuttles (producers) arrive with passengers and must board at the front of the bay.
Buses (consumers) depart from the rear, taking the rear-most group.
The bay has a fixed capacity (maximum groups it can hold).
If the bay is full, arriving VIPs (producers) must wait.
If the bay is empty, departing buses (consumers) must wait.
You must implement a thread-safe bounded blocking queue with:

	- BoundedBlockingQueue(int capacity) — set maximum capacity. 
	- void enqueue(int x) — add x to the front; block if full.
	- int dequeue() — remove and return the rear; block if empty.
	- int size() — return current count.

Multiple producer threads will only call enqueue; multiple consumer threads will only call 
dequeue. The judge may call size() after the test.

 Input Format:
 -------------
Num of Producers
Num of Consumers
OpCodes...space-separated integers
Args For Ops Needing Args

OpCodes:
-----------
0 → create the queue. Requires 1 argument (capacity).
1 → enqueue. Requires 1 argument (the element).
2 → dequeue. No argument; output the returned value.
3 → size. No argument; output the current size.

Output Format:
--------------
Print the sequence of results in the order operations are executed:
 - For every 2 (dequeue), print the dequeued value.
 - For every 3 (size), print the size at that moment.
 
Sample Input:
---------------
3
4
0 1 1 1 2 2 2 1
3 1 0 2 3

Sample Output:
--------------
1 0 2 1

Explanation:
-------------
So the operation stream is:
create(3)
enqueue(1)
enqueue(0)
enqueue(2)
dequeue() → outputs one of {1,0,2} depending on interleaving
dequeue() → outputs one of the remaining two
dequeue() → outputs the last remaining
enqueue(3)
size() → outputs 1 element remaining in the queue 
 */
import java.util.*;

import java.util.Deque;
import java.util.LinkedList;

class BlockingQueueCustom {
    private final Deque<Integer> buffer;
    private final int capacity;

    public BlockingQueueCustom(int capacity) {
        this.capacity = capacity;
        this.buffer = new LinkedList<>();
    }

    public synchronized void enqueue(int x) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.addFirst(x); 
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int val = buffer.removeLast(); 
        notifyAll();
        return val;
    }

    public synchronized int size() {
        return buffer.size();
    }
}


class Producer extends Thread {
    final BlockingQueueCustom buffer;
    int ops [];
    int nums[];
    Producer(BlockingQueueCustom buffer, int ops [], int nums []) {
        this.buffer = buffer;
        this.ops = ops;
        this.nums = nums;
    }

    @Override
    public void run() {
        try {
            for(int i = 1; i < ops.length; i++){
                if(ops[i]==1){
                    buffer.enqueue(nums[i]);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    final BlockingQueueCustom buffer;
    int ops[];
    int nums[];
    Consumer(BlockingQueueCustom buffer, int ops [], int nums[]) {
        this.buffer = buffer;
        this.ops = ops;
        this.nums = nums;
    }


    @Override
    public void run() {
        try {
            for(int i = 1; i < ops.length; i++){
                if(ops[i]==2){
                    System.out.print(buffer.dequeue()+" ");
                }
                else if(ops[i]==3){
                    System.out.print(buffer.size()+" ");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class BQ {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            cin.nextInt();
            cin.nextInt();
            cin.nextLine();
            String[] opsc = cin.nextLine().split(" ");
            String[] arguments = cin.nextLine().split(" ");
            int ops[] = new int[opsc.length];
            int nums[] = new int[ops.length];
            int k = 0;
            for (int i = 0; i < ops.length; i++) {
                ops[i] = Integer.parseInt(opsc[i]);
                if (ops[i] != 2) {
                    nums[i] = Integer.parseInt(arguments[k]);
                    k++;
                }
            }
            final BlockingQueueCustom buffer = new BlockingQueueCustom(nums[0]);
            Producer p = new Producer(buffer,ops,nums);
            Consumer c = new Consumer(buffer,ops,nums);
            p.start();
            c.start();

            p.join();
            c.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
