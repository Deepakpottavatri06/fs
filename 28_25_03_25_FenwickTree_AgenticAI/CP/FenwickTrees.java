    import java.util.*;

    public class FenwickTrees {
        int bit [];
        int len;
        FenwickTrees(int n){
            bit = new int[n+1];
            len = n;
        }
        void update(int i, int val){
            while(i<=len){
                bit[i]+=val;
                i+=(i&-i);
            }
        }
        int prefixSum(int i){
            // 1 based
            int val = 0;
            while(i>0){
                val +=bit[i];
                i-=(i&-i);
            }
            return val;
        }

        int rangeSum(int l , int r){
            // bit[l] need to be included!
            return prefixSum(r) - prefixSum(l-1);

        }
        public static void main(String[] args) {
            Scanner cin = new Scanner(System.in);
            int n = cin.nextInt();
            int arr [] =new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = cin.nextInt();
            }
            FenwickTrees binaryIT = new FenwickTrees(n);
            for (int i = 0; i < arr.length; i++) {
                binaryIT.update(i+1, arr[i]);
            }
            System.out.println(Arrays.toString(binaryIT.bit));
            System.out.println(binaryIT.rangeSum(1,5));
        }
    }
