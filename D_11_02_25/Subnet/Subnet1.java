/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/

import java.util.*;

public class Subnet1 {

    public static void main(String[] args) {
        Scanner cin  = new Scanner(System.in);
        int n = cin.nextInt();
        System.out.println(findMask(n));
        cin.close();
    }
    static String findMask(int prefixLength){
        int bitMask = prefixLength == 0 ? 0 : ~((1 << (32 - prefixLength)) - 1);
        return intToIp(bitMask);
    }
    public static String intToIp(int ip) {
        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 255,
                (ip >> 16) & 255,
                (ip >> 8) & 255,
                ip & 255);
    }
}