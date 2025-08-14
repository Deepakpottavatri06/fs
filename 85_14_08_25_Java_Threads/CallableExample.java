import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        // Step 1: Create Callable
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += i;
                Thread.sleep(200); // Simulating delay
            }
            return sum; // Returns result
        };

        // Step 2: Create ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Step 3: Submit Callable and get Future
        Future<Integer> future = executor.submit(task);

        // Step 4: Do something else while task runs
        System.out.println("Task submitted, doing other work...");

        // Step 5: Get result (blocks until ready)
        Integer result = future.get();
        System.out.println("Sum = " + result);
        executor.notify();
        executor.shutdown();
    }
}
