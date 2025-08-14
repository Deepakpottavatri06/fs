import java.util.*;

class SharedBuffer {
    final private Queue<Integer> buffer;
    final private int capacity = 5;

    SharedBuffer() {
        buffer = new LinkedList<>();
    }

    public synchronized void produce(int val) throws InterruptedException {
        while  (buffer.size() == capacity) {
            wait();
        }
        buffer.offer(val);
        System.out.println("Produced : " + val);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while  (buffer.isEmpty()) {
            wait();
            
        }
        int val = buffer.poll();
        
        notifyAll();
        return val;

    }
}

class Producer extends Thread {
    final SharedBuffer buffer;

    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                buffer.produce(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    final SharedBuffer buffer;
    final List<Integer> consumed = new ArrayList<>();
    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int val = buffer.consume();
                consumed.add(val);
                System.out.println(this.getName()+" : " + val);
            }
            System.out.println(this.getName()+" : "+consumed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class ProducerConsumer {
    public static void main(String[] args) {
        try {
            final SharedBuffer buffer = new SharedBuffer();
            Producer p = new Producer(buffer);
            Consumer c = new Consumer(buffer);
            Consumer c2 = new Consumer(buffer);

            p.start();
            c.start();
            c2.start();

            p.join();
            c.join();
            c2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
