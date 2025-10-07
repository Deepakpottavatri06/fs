/*You are working on a CPU workload optimizer for a gaming computer.
The processor executes a series of N operations, each represented by a numeric ID.
Every operation takes exactly 1 unit of time to execute.

However, due to thermal throttling, if the same operation type is executed 
repeatedly, the CPU must cool down for K time units before executing that 
same operation ID again. Different operations can be executed during 
the cooling period.

Your goal is to find the minimum total time required for the CPU to execute 
all operations without overheating.

Input Format
------------
Line-1: Two integers N and K representing the cool-down time between two identical operations.
Line-2: N space seperated integers representing operation IDs.

Output Format
-------------
Return a single integer — the minimum total time needed to finish all operations.

Constraints
-----------
1 ≤ number of operations ≤ 10 000
0 ≤ k ≤ 100
Operation IDs are positive integers


Sample Input:
-------------
4 2
1 2 1 1

Sample Output:
--------------
7


Explanation:
------------
The CPU runs operations in the order below (using _ to denote idle/
cool-down slots): 
        1 → 2 → idle → 1 → idle → idle → 1
            Total = 7 units */
import java.util.*;

public class CPUScheduler{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = cin.nextInt();
        }
        System.out.println(schedule(arr,n,k));
        cin.close();
    }
    static int schedule(int arr[], int n, int k){
        PriorityQueue<Integer> pq = new  PriorityQueue(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return b-a;
            }
            
        });
        Queue<int []> q = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        // for(Map.Entry<Integer,Integer> e: map.entrySet()){
        //     pq.add(e.getValue());
        // }
        pq.addAll(map.values());
        int time = 0;
        while(!pq.isEmpty() || !q.isEmpty()){
            if(!pq.isEmpty()){
                int mostFreq = pq.poll();
                mostFreq--;
                if(mostFreq!=0)q.offer(new int[]{mostFreq,time+k});
            }
            while(!q.isEmpty() && q.peek()[1]<=time){
                pq.add(q.poll()[0]);
            }
            time++;
        }
        
        return time;
    }
}