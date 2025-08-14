import java.util.Scanner;

public class UniqueName{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String tn  = cin.next();
        String mn = cin.next();
       
        System.out.println(find(tn,mn));
        
        
    }
    static String find(String tn, String mn){
        // StringBuilder res = new StringBuilder();
        // backtrack(tn,mn,0,0,res,new StringBuilder());
        
        // return res.toString();
        return twoPointer(tn,mn);
        
    }
    static String twoPointer(String tn, String mn){
        int i = 0;
        int j = 0;
        StringBuilder un = new StringBuilder();
        while(i<tn.length() && j < mn.length()){
            if(tn.charAt(i)>mn.charAt(j)){
                un.append(tn.charAt(i++));
            }
            else if(tn.charAt(i)<mn.charAt(j)){
                un.append(mn.charAt(j++));
            }
            else{
                String one = tn.substring(i);
                String two = mn.substring(j);
                if(one.compareTo(two)>=0){
                    un.append(tn.charAt(i++));
                }
                else{
                    un.append(mn.charAt(j++));
                }
            }
        }
        while(i<tn.length()){
            un.append(tn.charAt(i++));
        }
        
        while(j<mn.length()){
            un.append(mn.charAt(j++));
        }
        return un.toString();
    }
    static void backtrack(String tn, String un, int i , int j, StringBuilder res, StringBuilder curr){
        if(i== tn.length()|| j==un.length()){
            if(j<un.length()){
                curr.append(un.substring(j));
            }
            if(i<tn.length()){
                curr.append(tn.substring(i));
            }
            if(res.length()==0 || res.toString().compareTo(curr.toString())<0){
                res.setLength(0);
                res.append(curr.toString());
            }
            return;
           
        }
        char t = tn.charAt(i);
        char u = un.charAt(j);
        if(t>u){
            curr.append(t);
            backtrack(tn,un,i+1,j,res,curr);
        }
        else if(t<u){
            curr.append(u);
            backtrack(tn,un,i,j+1,res,curr);
        }
        else{
            int len = curr.length();
            curr.append(t);
            backtrack(tn,un,i+1,j,res,curr);
            curr.setLength(len);
            curr.append(u);
            backtrack(tn,un,i,j+1,res,curr);
            curr.setLength(len);
        }
        
    }
    
}