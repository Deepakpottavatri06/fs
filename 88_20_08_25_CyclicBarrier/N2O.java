import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
public class N2O{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        int nCount = 0;
        int oCount = 0;
        for(char c: inp.toCharArray()){
            if(c=='N'){
                nCount++;
            }
            else{
                oCount++;
            }
        }
        if(nCount!=2*oCount){
            System.out.println("Invalid");
            return;
        }
        AtomicInteger ind=  new AtomicInteger(1);
        CyclicBarrier cb = new CyclicBarrier(3,
       ()->{
           
           System.out.println("N2O - "+ind.getAndIncrement()+" is formed");
            
       });
        Semaphore ns = new Semaphore(2);
        Semaphore os = new Semaphore(1);
        for(int i = 0; i< nCount; i++){
            new N(cb,ns).start();
        }
        for(int i = 0; i < oCount; i++){
            new O(cb,os).start();
        }
        
        
    }
}

class N extends Thread{
    Semaphore ns;
    CyclicBarrier cb;
    N(CyclicBarrier cb, Semaphore ns){
        this.cb = cb;
        this.ns = ns;
    }
    
    @Override
    public void run(){
        try {
            ns.acquire();
            cb.await();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            ns.release();
        }
    }
}

class O extends Thread{
    Semaphore os;
    CyclicBarrier cb;
    O(CyclicBarrier cb, Semaphore os){
        this.cb = cb;
        this.os = os;
    }
    
    @Override
    public void run(){
        try {
            os.acquire();
            cb.await();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            os.release();
        }
    }
}