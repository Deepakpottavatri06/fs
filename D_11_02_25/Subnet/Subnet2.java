/*/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/

import java.util.Scanner;

public class Subnet2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String ip = cin.next();
        int cidr = cin.nextInt();
        findRange(ip,cidr);
        cin.close();
    }
    public static void findRange(String ip , int cidr){
        int bitMask = cidr==0? 0 : ~((1<<(32-cidr))-1);
        int intIp = ipToInt(ip);
        int networkId = intIp & bitMask;
        int broadcastId = intIp | ~bitMask;
        System.out.println(intToIp(networkId)+" "+intToIp(broadcastId));
    }
    public static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }
    public static String intToIp(int ip){
        return String.format("%d.%d.%d.%d",
            (ip>>24) & 255,
            (ip>>16) & 255,
            (ip>>8) & 255,
            (ip) & 255
        );
    }
}
