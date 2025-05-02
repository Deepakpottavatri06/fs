// You are given a crystal with an energy level n. Your goal is to discover all the 
// different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order doesn't matter.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []



// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.

// solution:

import java.util.*;

public class CrystalEnergy {
    public static void main(String[] args) {
        int n = 28;
        System.out.println(crystalEnergy(n));
    }

    public static List<List<Integer>> crystalEnergy(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n, 2);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int remaining, int start) {
        if (remaining == 1) {
            if (current.size() > 1) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (int i = start; i <= remaining; i++) {
            if (remaining % i == 0) {
                current.add(i);
                backtrack(result, current, remaining / i, i); // allow reuse of same factor
                current.remove(current.size() - 1);
            }
        }
    }
}
