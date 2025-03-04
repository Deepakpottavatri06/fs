import java.util.*;

class DisjointSet{
    int parent [] ;
    int rank [];
    DisjointSet(){
        parent = new int[26];
        rank = new int[26];
        for(int i = 0; i < 26 ; i++){
            parent[i] = i;
            rank[i] = i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public  boolean union(int x , int y){
        int rootx = find(x);
        int rooty = find(y);
        if(rootx==rooty){
            return false;
        }
        
        if(rank[rootx]>rank[rooty]){
            parent[rootx] = rooty;
        }
        else if(rank[rootx]<rank[rooty]){
            parent[rooty] = rootx;
        }
        else{
            parent[rootx] = rooty;
            rank[rootx]--;
        }
        return true;
    }
    
}

public class DecodeCipher{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        char key1 [] = cin.next().toCharArray();
        char key2 [] = cin.next().toCharArray();
        char text [] = cin.next().toCharArray();
        System.out.println(decodeCipher(key1,key2,text));
        cin.close();
    }
    static String decodeCipher(char key1 [] , char key2 [], char text []){
        DisjointSet set = new DisjointSet();
        int n = key1.length;
        for(int i = 0; i < n ; i++){
            set.union(key1[i]-97,key2[i]-97);
        }
        n = text.length;
        for(int i = 0; i < n ; i++){
            int j = set.find(text[i]-97);
            text[i]  = (char)(j + 97);
        }
        return new String(text);
        
    }
}