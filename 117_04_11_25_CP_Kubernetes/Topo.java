import java.util.*;

public class Topo{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        List<List<Integer>> map = new ArrayList<>();
        for(int i = 0; i < n; i++){
            map.add(new ArrayList<>());
        }
        for(int i = 0; i < n-1; i++){
            int u = cin.nextInt();
            int v = cin.nextInt();
            map.get(u).add(v);
            map.get(v).add(u);
        }
        
        System.out.println(find(map,n));
        cin.close();
    }
    static List<Integer> find(List<List<Integer>> map, int n){
        // System.out.println(map);
        int deg[] = new int[n];
        for(int i = 0; i < n; i++){
            deg[i] = map.get(i).size();
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(deg[i]==1){
                q.offer(i);
                deg[i]--;
            }
        }
        int remaining = n;
        while(remaining>2){
            System.out.println(q);
            remaining -= q.size();
            int size = q.size();
            for(int i = 0;i < size; i++){
                int top = q.poll();
                // System.out.println("top : "+top);
                // System.out.println(map.get(top));
                for(int node: map.get(top)){
                    deg[node]--;
                    if(deg[node]==1){
                        q.offer(node);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){
            res.add(q.poll());
        }
        return res;
        
    }
}