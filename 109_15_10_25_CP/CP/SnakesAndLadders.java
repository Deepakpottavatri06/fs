
import java.util.*;
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int flat [] = new int[n*n+1];
        boolean t = false;
        int ind = 1;
        for(int i = n-1; i >= 0; i--){
            if(t){
                for(int j = n-1; j >=0; j--){
                    flat[ind++] = board[i][j]; 
                }
            }
            else{
                for (int j = 0; j < n; j++) {
                    flat[ind++] = board[i][j];
                }
            }
            t = !t;
        }

        return bfs(flat,n);
    }
    public int bfs( int flat [] ,int n){
        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{1,0});
        Set<Integer> set = new HashSet<>();
        set.add(1);
        while(!q.isEmpty()){
            int top [] = q.poll();
            if(top[0]==n*n){
                return top[1];
            }
            for (int i = 1; i < 7; i++) {
                if(top[0]+i>n*n) continue;
                int next = top[0]+i;
                if(flat[top[0]+i]!=-1){
                    next = flat[top[0]+i];
                }
                if (set.contains(next)) continue;
                set.add(next);
                q.add(new int[]{next,1+top[1]});
            }
        }
        return 0;
    }
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
			int n = cin.nextInt();
			int board[][] = new int[n][n];
			for(int i = 0; i < n; i++){
			    for(int j = 0; j < n; j++){
			        board[i][j] = cin.nextInt();
			    }
			}
			System.out.println(new SnakesAndLadders().snakesAndLadders(board));
		}
    }
}