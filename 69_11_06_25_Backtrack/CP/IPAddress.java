/*Pramod is planning to design a program, which helps to create 
the IP addresses posssible from a given string S, 
where each IP address should be valid.

It is guaranteed that S contains only digits.

Can you help pramod in designing such program, which returns all possible 
IP addresses. Print the answer in lexicographic order.

NOTE:
-----

- A valid IP address consists of exactly four integers, 
each integer is between 0 and 255, separated by single dots 
and cannot have leading zeros
- IP Addresses are said to be valid if it falls in the range 
from 0.0.0.0 to 255.255.255.255

- IP addresses like [123.012.234.255 , 123.234.345.34] are invalid.

Input Format:
-------------
A string S, contains only digits [0-9].

Output Format:
--------------
Print all possible IP addresses which are valid.


Sample Input-1:
---------------
23323311123

Sample Output-1:
----------------
[233.233.11.123, 233.233.111.23]


Sample Input-2:
---------------
12345678

Sample Output-2:
----------------
[1.234.56.78, 12.34.56.78, 123.4.56.78, 123.45.6.78, 123.45.67.8]


Sample Input-3:
---------------
02550255

Sample Output-3:
----------------
[0.25.50.255, 0.255.0.255]
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class IPAddress{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String ip = cin.next();
            
            System.out.println(find(ip));
        }
    }
    static List<String> find(String ip){
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(0,0,sb,ip,res);
        return res;
    }
    static void backtrack(int ind, int count, StringBuilder sb, String ip, List<String> res){
        
        if(count==3){
            if(ind>=ip.length()){
                return;
            }
            if(isValid(ip.substring(ind))){
                int len = sb.length();
                sb.append(ip.substring(ind));
                res.add(sb.toString());
                sb.setLength(len);
            }
            return;
        }

        for(int i = ind; i < Math.min(ind+3,ip.length()); i++){
            int len = sb.length();
            String part = ip.substring(ind,i+1);
            if(!isValid(part))continue;
            sb.append(part).append(".");
            backtrack(i+1,count+1,sb,ip,res);
            sb.setLength(len);
        }
        
    }
    static boolean isValid(String part){
        if(part.length()>1 && part.charAt(0)=='0') return false;
        int in = Integer.parseInt(part);
        return in<256 && in>=0;
    }
}