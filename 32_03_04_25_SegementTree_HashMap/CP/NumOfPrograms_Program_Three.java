/*In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
Organized at HITEX. In HITEX, there are a lot of programs planned. During 
the festival in order to maintain the rules of Pandemic, they put a 
constraint that one person can only attend any one of the programs in 
one day according to planned days.

Now it’s your aim to implement the "Solution" class in such a way that 
you need to return the maximum number of programs you can attend according 
to given constraints.

Explanation:
You have a list of programs 'p' and days 'd', where you can attend only 
one program on one day. Programs [p] = [first day, last day], 
p is the program's first day and the last day.


Input Format:
-------------
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

Output Format:
--------------
An integer, the maximum number of programs you can attend.


Sample Input-1:
---------------
4
1 2,2 4,2 3,2 2

Sample Output-1:
----------------
4

Sample Input-2:
---------------
6
1 5,2 3,2 4,2 2,3 4,3 5

Sample Output-2:
----------------
5
 */

 import java.util.*;


class SegmentTree{
    int n ;
    int tree [];
    SegmentTree(int arr []){
        n = arr.length;
        tree = new int[n*4];
        build(arr,0,0,n-1);
    }
    void build(int arr[], int v, int l, int r){
        if(l==r){
            tree[v] = arr[l];
        }
        else{
            int mid = (l+r)/2;
            build(arr,v*2 + 1,l,mid);
            build(arr,v*2 + 2,mid+1,r);
            tree[v] = Math.min(tree[v*2 + 1],tree[v*2 + 2]);
        }
    }
    int minRange(int l, int r){
        return min(0,0,n-1,l,r);
    }
    void update(int v, int l, int r ,int pos , int new_val){
        if(l==r){
            tree[v] = new_val;
        }
        else{
            int mid = (l+r)/2;
            if(pos<=mid){
                update(v*2 + 1,l,mid,pos,new_val);
            }
            else{
                update(v*2 + 2,mid+1,r,pos,new_val);
            }
            tree[v] = Math.min( tree[v*2 + 1] , tree[v*2 + 2]);
        }
    }
    int min(int v, int treeL, int treeR , int rangeL , int rangeR){
        if(rangeL>rangeR){
            return Integer.MAX_VALUE;
        }
        if(rangeL<=treeL && rangeR>=treeR){
            return tree[v];
        }
        int mid = (treeL + treeR)/2;
        return Math.min(min(v*2 +1 ,treeL,mid,rangeL,Math.min(rangeR,mid)),min(v*2 + 2,mid+1,treeR,Math.max(rangeL,mid+1),rangeR));
    }
    
}

public class NumOfPrograms_Program_Three{
    public static void main(String [] args ){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        cin.nextLine();
        String p [] = cin.nextLine().split(",");
        int programs[][] = new int[n][2];
        int max = 0;
        for(int i = 0; i < n; i++){
            String days [] = p[i].split(" ");
            programs[i] = new int[]{Integer.parseInt(days[0]),Integer.parseInt(days[1])};
            max = Math.max(max,programs[i][1]);
        }
        System.out.println(find(n,programs,max));
        cin.close();
    }
    static int find(int n, int programs[][], int max){
        Arrays.sort(programs,(a,b)->a[1]==b[1]?b[0]-a[0]:a[1]-b[1]);
        int days [] = new int[max+1];
        SegmentTree t = new SegmentTree(days);
        int res = 0;
        for(int i = 0; i < n; i++){
            int curr [] = programs[i];
            for(int d = curr[0]; d<=curr[1]; d++){
                if(t.minRange(d,d)==0){
                    t.update(0,0,t.n-1,d,1);
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}