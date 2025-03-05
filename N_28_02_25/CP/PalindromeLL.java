/*Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
--------------
Print a boolean a value.


Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false */
import java.util.*;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
    }
}

public class PalindromeLL{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int arr[] = new int[inp.length];
        for(int i = 0; i < inp.length; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        Node head = constructLL(arr,arr.length);
        // trav(head);
        System.out.println(findPalindrome(head));
        cin.close();
    }
    static Node constructLL(int arr[], int n){
        if(n==0){
            return null;
        }
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1 ; i < n ; i++){
            Node t = new Node(arr[i]);
            temp.next = t;
            temp = t;
        }
        return head;
    }
    static void trav(Node head){
        if(head==null){
            return;
        }
        System.out.println(head.val);
        trav(head.next);
    }
    static boolean findPalindrome(Node head){
        if(head == null){
            return false;
        }
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        Node mid = slow.next;
        slow.next = null;
        mid = reverseLL(mid);
        // trav(mid);
        // System.out.println(" ");
        Node first = head;
        // trav(first);
        while(mid!=null){
            if(first.val!=mid.val){
                return false;
            }
            first = first.next;
            mid = mid.next;
        }
        return true;
    }
    static Node reverseLL(Node node){
        if(node ==null){
            return null;
        }
        
        // Stack<Node> st = new Stack<>();
        // Node temp = node;
        // while(temp!=null){
            
        //     st.push(temp);
        //     temp = temp.next;
        //     st.peek().next = null;
        // }
        // Node newMid = st.pop();
        // temp = newMid;
        // while(!st.empty()){
        //     Node t = st.pop();
        //     temp.next = t;
        //     temp = t;
        // }
        // return newMid;
        
        Node curr = node;
        Node prev = null;
        while(curr!=null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}