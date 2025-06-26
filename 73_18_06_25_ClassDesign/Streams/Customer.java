/*Given a list of CustomerPurchase objects, each representing a purchase 
with customerId, customerName, purchaseAmount, and category. 
Compute the tier of customers based on total purchases 
(only counting purchases ≥ 500.0).

Tiers:
- Platinum ≥ 5000
- Gold ≥ 2000 and < 5000
- Silver < 2000

Note: Display the customers in descending order of their purchases of same tier.

Example 1
---------

Input:
4
C101 Alice 1200 Electronics
C102 Bob 499 Books
C101 Alice 4500 Travel
C103 Charlie 800 Furniture

Output:
C101 Alice : Platinum
C103 Charlie : Silver


Example 2
----------
Input:
8
C801 Mia 1000 Electronics
C801 Mia 1200 Furniture
C801 MIA 3000 Lighting
C802 Olivia 1001 Apparel
C803 Emma 1999 Jewelry
C803 Emma 1 Books
C804 Ava 2000 Appliances
C805 Mia 1000 Garden

Output:
C801 Mia : Platinum
C804 Ava : Gold
C803 Emma : Silver
C802 Olivia : Silver
C805 Mia : Silver
 */
import java.util.*;
import java.util.stream.Collectors;
class CustomerPurchase{
    String customerId; 
    String customerName;
    int purchaseAmount;
    String category;
    
    CustomerPurchase(String customerId,String customerName, int purchaseAmount, String category){
        this.customerId = customerId;
        this.customerName = customerName;
        this.purchaseAmount = purchaseAmount;
        this.category = category;
    }
    
    
}

public class Customer{
    public static String captalize(String s){
        return s.substring(0,1).toUpperCase()+s.substring(1).toLowerCase();
    }
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        List<CustomerPurchase> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new CustomerPurchase(cin.next(),captalize(cin.next()),Integer.parseInt(cin.next()),cin.next()));
        }
        
        List<String> res = 
        list.stream().
        filter(c->c.purchaseAmount>=500)
        .collect(
            Collectors.groupingBy(c->c.customerId+" "+c.customerName,
            Collectors.summingDouble(c->c.purchaseAmount)))
        .entrySet().stream()
        .sorted((a,b)->{
            int d = Double.compare(b.getValue(), a.getValue());
            // if(d==0){
            //     return a.getKey().split(" ")[0].compareTo(b.getKey().split(" ")[0]);
            // }
            return d;
        })
        .map(c->{
            
            String membership = (c.getValue()>=5000)? "Platinum" : c.getValue()>=2000?"Gold":"Silver";
            return c.getKey()+" : " + membership;
        })
        .collect(
            Collectors.toList());
            
        for(String i:res){
            System.out.println(i);
        }
        
        
    }
}