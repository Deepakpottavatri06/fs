/*You are building an E-Commerce Product Insights Engine for a marketplace like 
Amazon or Flipkart. The platform stores information about various products, 
their pricing history, and user ratings.

Your job is to:

    1. Accept data for multiple products.
    
    2. Each product has:
        🎯 Multiple price entries (date + price)
        🎯 Multiple ratings (user + stars out of 5)
        
    3. Calculate:
        🎯  Average price of the product
        🎯 Price volatility score: Standard deviation of prices
        🎯 Average rating

    4. Classify products into Insight Tiers:
        🟢 Stable & Loved: Volatility < 100 and Rating ≥ 4.0
        🟡 Unstable but Popular: Volatility ≥ 100 and Rating ≥ 4.0
        🔴 Unstable & Poorly Rated: Volatility ≥ 100 and Rating < 4.0
        ⚪ Stable but Low-Rated: Volatility < 100 and Rating < 4.0
        
Sample Input:
-------------
2               
EchoDot         
3               // Number of price entries
2024-06-01 3499 // priceDate priceAmount
2024-06-10 3299
2024-06-15 3599
2               // Number of ratings
Alice 5         // userName stars
Bob 4
OldTV           // ProductName
4               // Number of price entries
2024-05-01 9999 // priceDate priceAmount
2024-05-10 10999
2024-05-15 11999
2024-05-20 8999
3               // Number of ratings
Charlie 2       // userName stars
Diana 3
Eve 1

No comments sample: 
2
EchoDot
3
2024-06-01 3499 
2024-06-10 3299
2024-06-15 3599
2
Alice 5
Bob 4
OldTV  
4
2024-05-01 9999 
2024-05-10 10999
2024-05-15 11999
2024-05-20 8999
3
Charlie 2
Diana 3
Eve 1

Sample Output:
--------------
Product: EchoDot, AvgPrice: 3465.7, Volatility: 124.7, AvgRating: 4.5, Tier: Unstable but Popular
Product: OldTV, AvgPrice: 10499.0, Volatility: 1118.0, AvgRating: 2.0, Tier: Unstable & Poorly Rated

 */
import java.util.*;

public class Ecommerce{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = Integer.parseInt(sc.nextLine());

            List<Product> products = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String name = sc.nextLine();
                int m = Integer.parseInt(sc.nextLine());

                List<PriceEntry> prices = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    String[] parts = sc.nextLine().split(" ");
                    prices.add(new PriceEntry(parts[0], Double.parseDouble(parts[1])));
                }

                int k = Integer.parseInt(sc.nextLine());
                List<Rating> ratings = new ArrayList<>();
                for (int j = 0; j < k; j++) {
                    String[] parts = sc.nextLine().split(" ");
                    ratings.add(new Rating(parts[0], Integer.parseInt(parts[1])));
                }

                products.add(new Product(name, prices, ratings));
            }

            InsightEngine engine = new InsightEngineImpl();
            // System.out.println("=== Product Insights Summary ===");
            for (Product p : products) {
                ProductInsight insight = engine.analyze(p);
                System.out.println(insight);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

// TODO: Complete this POJO
class PriceEntry {
    // String date; double amount
    private String date;
    private double amount;
    PriceEntry(String d, double a){
        this.date = d;
        this.amount = a;
    }
    public String getDate(){
        return this.date;
    }
    public double getAmount(){
        return this.amount;
    }
}

// TODO: Complete this POJO
class Rating {
    // String userName; int stars
    private String userName;
    private int stars;
    Rating(String un, int st){
        this.userName = un;
        this.stars =  st;
    }
    public String getUserName(){
        return this.userName;
    }
    public int getStars(){
        return this.stars;
    }
}

// TODO: Complete this POJO
class Product {
    // String name; List<PriceEntry>; List<Rating>
    private String name;
    private List<PriceEntry> priceEntry;
    private List<Rating> rating;
    Product(String name, List<PriceEntry> priceEntry,List<Rating> rating ){
        this.name = name;
        this.priceEntry = priceEntry;
        this.rating = rating;
    }
    public String getName(){
        return this.name;
    }
    public List<PriceEntry> getPriceEntry(){
        return this.priceEntry;
    }
    public List<Rating> getRating(){
        return this.rating;
    }
}

// TODO: Complete this POJO
class ProductInsight {
    // Product; double avgPrice; double volatility; double avgRating; String insightTier
    // Override toString() for output
    Product product; 
    double avgPrice; 
    double volatility; 
    double avgRating; 
    String insightTier;
    ProductInsight(Product product, double avgPrice, double volatility, double avgRating, String insightTier){
        this.product = product;
        this.avgPrice = avgPrice;
        this.volatility = volatility;
        this.avgRating = avgRating;
        this.insightTier = insightTier;
    }
    
    @Override
    public String toString(){
        return String.format("Product: %s, AvgPrice: %.1f, Volatility: %.1f, AvgRating: %.1f, Tier: %s",
        product.getName(),avgPrice,volatility,avgRating,insightTier);
    }
    
}

interface InsightEngine {
    ProductInsight analyze(Product p);
}

// TODO: Implement InsightEngineImpl using Math.pow and Math.sqrt for std deviation
class InsightEngineImpl implements InsightEngine {
    public ProductInsight analyze(Product p) {
        // Logic:
        // - Calculate avgPrice
        // - Calculate standard deviation
        // - Calculate avgRating
        // - Tier assignment
        double avgPrice = 0.0;
        double avgRating =0.0;
        int n = 0;
        for(PriceEntry pe: p.getPriceEntry()){
            n++;
            avgPrice +=pe.getAmount();
        }
        avgPrice/=n;
        double std = 0.0;
        for(PriceEntry pe: p.getPriceEntry()){
            double diff = (pe.getAmount()-avgPrice);
            std += diff*diff;
        }
        std/=n;
        std = Math.sqrt(std);
        int noOfRating  = 0;
        for(Rating r: p.getRating()){
            noOfRating++;
             avgRating += r.getStars();
        }
        avgRating/=noOfRating;
        String tier = "";
        if(std < 100 && avgRating >= 4.0){
            tier = "Stable & Loved"; 
        }
        else if(std >= 100 && avgRating >= 4.0){
            tier = "Unstable but Popular";
        }
        else if(std >= 100 && avgRating < 4.0){
            tier = "Unstable & Poorly Rated";
        }
        else{
            tier = "Stable but Low-Rated";
        }
        return new ProductInsight(p,avgPrice,std,avgRating,tier);
        // return null; // TODO
    }
}
