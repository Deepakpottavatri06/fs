/*
Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
whether IP-1 and IP-2 belongs to same host range or not.

Input Format:
---------------
Two space separated strings, IP1 and IP2.
An integer, CIDR (subnet mask).

Output Format:
---------------
A boolean result.


Sample Input-1:
-----------------
192.168.1.10 192.168.1.20
24

Sample Output-1:
------------------
true


Sample Input-2:
-----------------
192.0.2.1 192.0.3.253
24

Sample Output-2:
------------------
false

 */

import java.util.Scanner;

public class Subnet3 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String ip1 = cin.next();
        String ip2 = cin.next();
        int prefixLength = cin.nextInt();
        System.out.println(find(ip1,ip2,prefixLength));
        cin.close();
    }
    static boolean find(String ip1, String ip2, int prefixLength){
        int bitMask = 0xFFFFFFFF <<(32-prefixLength);
        int intIp1 = ipToInt(ip1);
        int intIp2 = ipToInt(ip2);
        int firstNetwork = intIp1 & bitMask;
        int secondNetwork = intIp2 & bitMask;
        return (firstNetwork==secondNetwork);
    }
    static int ipToInt(String ip){
        String temp [] = ip.split("\\.");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);
        int c = Integer.parseInt(temp[2]);
        int d = Integer.parseInt(temp[3]);
        return (a<<24) | (b<<16) | (c<<8) | d;
    }

}
