/*In computer networking, subnetting is the process of dividing a larger IP network
into multiple smaller subnetworks (subnets). Given a base network IP address, 
a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
write a Java program that calculates the starting addresses of all the subnets.

Your program should take as input:
	- A base network address (e.g., 192.168.1.0).
	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
	- The number of subnets to generate.

The program should then compute and return the starting addresses of each subnet.

Input Format:
-------------
A string NIP: The base network IP address (e.g., "192.168.1.0").
An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
An integer numSubnets: The number of subnets to be generated.

Output Format:
--------------
A list of subnet addresses, each representing the starting address of a subnet.


Sample Input:
-------------
192.168.1.0
26
4

Sample Output:
--------------
[192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

Explanation:
------------
A /26 subnet has 64 IP addresses. The starting addresses of 
the first four subnets are:
192.168.1.0/28, 
192.168.1.16/28, 
192.168.1.32/28, 
192.168.1.48/28

Sample Input:
192.168.1.0
24
4

Sample Output:
--------------
[192.168.1.0/26, 192.168.1.64/26, 192.168.1.128/26, 192.168.1.192/26]

Explanation:
------------
A /24 subnet has 256 IP addresses. The starting addresses of 
the first four subnets are:
192.168.1.0/26, 
192.168.1.64/26, 
192.168.1.128/26, 
192.168.1.192/26
 */

 import java.util.*;
public class subnet4 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String ip = cin.nextLine();
        int mask = cin.nextInt();
        int num = cin.nextInt();
        System.out.println(findSubnets(ip,mask,num));
        cin.close();
    }
    static List<String> findSubnets(String ip, int mask, int num){
        int bits = 0;
        while((int)Math.pow(2, bits)<num){
            bits++;
        }
        List<String> res =  new ArrayList<>();
        int totalBits = bits+mask;
        int mastInt = ~0 <<(32-mask);
        int newMaskInt = ~0 <<(32-totalBits);
        int ipInt = ipToInt(ip);
        for (int i = 0; i < num; i++) {
            int subnet = (ipInt&newMaskInt) | (i<<(32-totalBits));
            res.add(intToIp(subnet)+"/"+totalBits);
        }
        return res;
    }
    public static String intToIp(int ip) {
        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 255,
                (ip >> 16) & 255,
                (ip >> 8) & 255,
                ip & 255);
    }
    public static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

}
