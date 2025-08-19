import java.util.concurrent.CyclicBarrier;

class Worker extends Thread {
    private CyclicBarrier barrier;
    private int id;
    static double maxTimeTaken [] = new double[3];
    public Worker(CyclicBarrier barrier, int id) {
        this.barrier = barrier;
        this.id = id;
    }

    static synchronized  void updateMaxTime(double time, int puzzle){
        if(maxTimeTaken[puzzle]<time){
            maxTimeTaken[puzzle] = time;
        }
    }

    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 3; i++) {
                long t1 = System.currentTimeMillis();
                System.out.println("Thread " + id + " is working on puzzle : "+i);
                Thread.sleep((int) (Math.random() * 2000));
                System.out.println("Thread " + id + " waiting at barrier...");
                long t2 = System.currentTimeMillis();
                System.out.println(((double)(t2 - t1))/1000 + " Time taken for puzzle "+i+" by thread "+id);
                updateMaxTime(((double)(t2 - t1))/1000 , i);
                barrier.await();
            }

            // System.out.println("Thread " + id + " continues after barrier...");
            barrier.await();
            if(id==1){
                for (int i = 0; i < 3; i++) {
                    System.out.println("The max time taken for puzzle "+i+" :"+maxTimeTaken[i]);   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class FourFriends {
    public static void main(String[] args) throws Exception{
        int friends = 4;
        CyclicBarrier cb = new CyclicBarrier(friends);
        
        Worker workers [] = new Worker[friends];
        for(int i = 0; i < friends; i++){
            workers[i] = new Worker(cb, i+1);
            workers[i].start();
        }

    }
}
