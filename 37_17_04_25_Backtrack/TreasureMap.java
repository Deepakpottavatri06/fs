// public In a forgotten realm, explorers often find ancient treasure maps written as 
// long strings of mysterious characters with no spaces. Luckily, they also carry 
// an ancient wordbook (pathBook) containing all the known names of places, 
// landmarks, and directions.

// Your task is to help the explorer decode the map by inserting spaces such that 
// each segment is a valid location or direction from the pathBook. Return all 
// possible ways to break the map string into a valid sequence of known locations.

// You can reuse entries from the pathBook as many times as needed.

// Example 1:
// ----------
// input:
// deserttemplegolds			//Trasure map
// desert temple gold golds	//pathBook

// output:
// [desert temple gold]

// Explanation: The map can be decoded directly into three known places.

// Example 2:
// ----------
// input:
// forestforesthill
// forest hill

// output:
// [forest forest hill]

// Explanation: The explorer can reuse 'forest' more than once.

// Example 3:
// ----------
// input:
// oceanmountaintemple
// mountain temple

// output:
// []

// Explanation: The map begins with 'ocean', which is missing from the pathBook, so no decoding is possible.


// Map Decoding Constraints:
// - 1 <= map.length <= 20
// - 1 <= pathBook.length <= 1000
// - 1 <= pathBook[i].length <= 10
// - All strings consist of lowercase English letters.
// - All entries in pathBook are unique.
// - Input is structured so the total number of valid decoded strings does not exceed 10^5. TreasureMap {
    
// }
import java.util.*;

public class TreasureMap {
    public static void main(String[] args) {
        String map = "deserttemplegolds";
        List<String> pathBook = Arrays.asList("desert", "temple", "gold", "golds");
        System.out.println(decodeMap(map, pathBook));
    }

    public static List<String> decodeMap(String map, List<String> pathBook) {
        Set<String> dict = new HashSet<>(pathBook);
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(map, 0, dict, memo);
    }

    private static List<String> backtrack(String map, int start, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> result = new ArrayList<>();
        if (start == map.length()) {
            result.add("");  // Base case: valid path
            return result;
        }

        for (int end = start + 1; end <= map.length(); end++) {
            String word = map.substring(start, end);
            if (dict.contains(word)) {
                List<String> suffixes = backtrack(map, end, dict, memo);
                for (String suffix : suffixes) {
                    if (suffix.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + suffix);
                    }
                }
            }
        }

        memo.put(start, result);
        return result;
    }
}