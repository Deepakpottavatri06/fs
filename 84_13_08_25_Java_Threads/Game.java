/*There are a group of kids playing a game in a circle to test their arithmetic division skills.
The game starts with Kid 1, who says the number 1 to begin. Then, in clockwise order, each kid 
takes a turn and says the next number, but with the following rules:
 - If the number is divisible by 3 (but not by 5), the kid says "Hi".
 - If the number is divisible by 5 (but not by 3), the kid says "Hello".
 - If the number is divisible by both 3 and 5, the kid says "HiHello".
 - Otherwise, the kid simply says the number itself.

The same game rules are now implemented in a multi-threaded environment.
We are given an instance of a HiHelloGame class, which has four methods:

 - hi(printHi) → Prints "Hi" when the number is divisible by 3 only.
 - hello(printHello) → Prints "Hello" when the number is divisible by 5 only.
 - hiHello(printHiHello) → Prints "HiHello" when the number is divisible by both 3 and 5.
 - number(printNumber) → Prints the number itself when none of the above conditions are met.

The same instance of HiHelloGame will be shared across four threads:

Thread A: Calls hi() to output "Hi".
Thread B: Calls hello() to output "Hello".
Thread C: Calls hiHello() to output "HiHello".
Thread D: Calls number() to output the number.

Your task is to modify the HiHelloGame class so that it outputs the correct sequence for numbers 
from 1 to N in the correct order according to the rules above.

Input Format:
-------------
Line-1: An integer N.

Output Format:
--------------
Print a string array[].

Constraints:
• 1 <= n <= 10^4
 
 
Sample Input-1:
---------------
5

Sample Output-1:
----------------
1 2 Hi 4 Hello


Sample Input-2:
---------------
15

Sample Output-2:
----------------
1 2 Hi 4 Hello Hi 7 8 Hi Hello 11 Hi 13 14 HiHello 
 */
import java.util.*;

class HiHelloGame {
    int start = 1;
    final int maxValue;

    HiHelloGame(int m) {
        this.maxValue = m;
    }

    public synchronized void hi() throws InterruptedException {
        while (start <= maxValue) {
            while (start <= maxValue && !(start % 3 == 0 && (start % 5 != 0))) {
                // System.out.println("A waits");
                wait();
            }
            if (start > maxValue) {
                notifyAll();
                return;
            }
            start++;
            System.out.print("Hi ");
            notifyAll();
        }
    }

    public synchronized void hello() throws InterruptedException {
        while (start <= maxValue) {
            while (start <= maxValue && !(start % 5 == 0 && start % 3 != 0)) {
                // System.out.println("B waits");
                wait();
            }
            if (start > maxValue) {
                notifyAll();
                return;
            }
            start++;
            System.out.print("Hello ");
            notifyAll();

        }
    }

    public synchronized void hiHello() throws InterruptedException {
        while (start <= maxValue) {
            while (start <= maxValue && !(start % 5 == 0 && start % 3 == 0)) {
                // System.out.println("C waits");
                wait();
            }
            if (start > maxValue) {
                notifyAll();
                return;
            }
            start++;
            System.out.print("HiHello ");
            notifyAll();

        }
    }

    public synchronized void number() throws InterruptedException {
        while (start <= maxValue) {
            while (start <= maxValue && (start % 5 == 0 || start % 3 == 0)) {
                // System.out.println("D waits");
                wait();
            }
            if (start > maxValue) {
                notifyAll();
                return;
            }
            System.out.print(start + " ");
            start++;
            notifyAll();
        }
    }

}

class A extends Thread {
    HiHelloGame h;

    A(HiHelloGame h) {
        this.h = h;
    }

    @Override
    public void run() {
        try {
            h.hi();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class B extends Thread {
    HiHelloGame h;

    B(HiHelloGame h) {
        this.h = h;
    }

    @Override
    public void run() {
        try {
            h.hello();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class C extends Thread {
    HiHelloGame h;

    C(HiHelloGame h) {
        this.h = h;
    }

    @Override
    public void run() {
        try {
            h.hiHello();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class D extends Thread {
    HiHelloGame h;

    D(HiHelloGame h) {
        this.h = h;
    }

    @Override
    public void run() {
        try {
            h.number();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class Game {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int maxValue = cin.nextInt();
            HiHelloGame obj = new HiHelloGame(maxValue);
            try {
                A a = new A(obj);
                B b = new B(obj);
                C c = new C(obj);
                D d = new D(obj);

                a.start();
                b.start();
                c.start();
                d.start();

                a.join();
                b.join();
                c.join();
                d.join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}