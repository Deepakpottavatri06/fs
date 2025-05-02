/*In a magical kingdom, each wizard carries a certain number of enchanted crystals. 
A pair of wizards is said to have a "dominant wizard" if the first wizard, who 
\arrived earlier at the royal gathering, possesses more than twice as many 
crystals as the second wizard, who arrived later.

Given an list of crystals, representing the number of enchanted crystals carried 
by each wizard in their order of arrival at the gathering, calculate the number 
of "dominant wizard" pairs.

A pair of wizards (x, y) is considered dominant if:

- 0 ≤ x < y < crystals.length and
- crystals[x] > 2 × crystals[y]

Example 1:
Input: 
1 3 2 3 1
Output: 2

Explanation: The dominant wizard pairs are:
- Wizard 1 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1

Example 2:
Input:
2 4 3 5 1
Output: 3

Explanation: The dominant wizard pairs are:
- Wizard 1 (4 crystals) and Wizard 4 (1 crystal), since 4 > 2 × 1
- Wizard 2 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
- Wizard 3 (5 crystals) and Wizard 4 (1 crystal), since 5 > 2 × 1

Constraints:
- 1 ≤ crystals.length ≤ 5 × 10^4
- -2^31 ≤ crystals[i] ≤ 2^31 - 1 */
import java.util.Scanner;

class Item {
    double key;
    double p;
    int count;
    Item left;
    Item right;

    Item(double key) {
        this.key = key;
        p = Math.random();
        left = null;
        right = null;
        count = 1;
    }

    static int getCount(Item i) {
        if (i != null) {
            return i.count;
        }
        return 0;
    }

    static void updateCount(Item i) {
        if (i != null) {
            i.count = 1 + getCount(i.left) + getCount(i.right);
        }
    }
}

public class Crystals {
    static Item[] split(Item root, double key) {
        if (root == null) {
            return new Item[] { null, null };
        }

        if (root.key <= key) {
            Item rSplit[] = split(root.right, key);
            root.right = rSplit[0];
            Item.updateCount(root);
            return new Item[] { root, rSplit[1] };
        } else {
            Item lSplit[] = split(root.left, key);
            root.left = lSplit[1];
            Item.updateCount(root);
            return new Item[] { lSplit[0], root };
        }
    }

    static Item merge(Item left, Item right) {
        if (left == null || right == null) {
            return (left == null) ? right : left;
        }
        if (left.p > right.p) {
            left.right = merge(left.right, right);
            Item.updateCount(left);
            return left;
        } else {
            right.left = merge(left, right.left);
            Item.updateCount(right);
            return right;
        }
    }

    static Item insert(Item root, double key) {
        if (root == null) {
            return new Item(key);
        }

        Item split[] = split(root, key);
        Item newItem = new Item(key);
        return merge(merge(split[0], newItem), split[1]);

    }

    static int countLessThan(Item root, double k){
        if(root==null){
            return 0;
        }
        if(root.key<k){
            return 1 +  Item.getCount(root.left) + countLessThan(root.right, k);
        }
        else{
            return  countLessThan(root.left, k);
        }
    }
    static int reversePairs(int arr[]){

        Item root = null;
        int count = 0;
        for(int i = arr.length-1; i >=0 ; i--){
            count += countLessThan(root, arr[i]/2.0);
             root = insert(root, arr[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();

        String inp [] = sc.nextLine().split(" ");
        int n = inp.length;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(inp[i]);

        System.out.println(reversePairs(arr));
        sc.close();
    }
}
