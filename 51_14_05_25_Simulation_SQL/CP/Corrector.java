

import java.util.*;

public class Corrector{
    public static boolean isSame(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        Set<Character> set = Set.of('a','e','i','o','u','A','E','I','O','U');
        for(int i = 0;i<s1.length();i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(set.contains(c1) && set.contains(c2)){
                continue;
            }
            else if(set.contains(c1) ^ set.contains(c2)){
                return false;
            }
            else if(Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                    return false;
            }
            
        }
        return true;
    }
    public static List<String> getWords(String[] dict, String[] userwords){
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        for(String i : dict){
            set.add(i);
        }
        for(String i : userwords){
            if(set.contains(i)){
                res.add(i);
                continue;
            }
            boolean check = false;
            for(String j : dict){
                if(i.equalsIgnoreCase(j)){
                    res.add(j);
                    check = true;
                    break;
                }
            }
            if(check){
                continue;
            }
            for(String j : dict){
                if(isSame(i,j)){
                    res.add(j);
                    check = true;
                    break;
                }
            }
            if(!check){
                res.add(" ");
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(",");
        String[] userwords = sc.nextLine().split(",");
        System.out.println(getWords(dict,userwords));
        sc.close();
    }
}