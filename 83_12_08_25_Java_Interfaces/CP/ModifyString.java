import java.util.Scanner;

public class ModifyString{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp = cin.nextLine();
            int arr [] = new int[inp.length()];
            for(int i = 0; i < inp.length(); i++){
                arr[i] = cin.nextInt();
            }
            
            System.out.println(find(arr,inp));
        }
    }
    static String find(int arr[], String inp){
        int n = inp.length();
        char temp[] = inp.toCharArray();
        int revPrefix [] = new int[n];
        revPrefix[n-1] = arr[n-1];
        for(int i = n-2; i>=0; i--){
            revPrefix[i] = revPrefix[i+1] + arr[i]; 
        }
        for(int i = 0; i < n; i++){
            // System.out.println("i = "+i);
            // for(int j = 0; j<=i; j++){
            //     temp[j] = (char)((int)temp[j] + (arr[i]%26));
            //     // System.out.println("  - "+temp[j]);
            // }
            temp[i] = (char)('a' + (temp[i]-'a'+revPrefix[i])%26);
        }
        return new String(temp);
    }
}