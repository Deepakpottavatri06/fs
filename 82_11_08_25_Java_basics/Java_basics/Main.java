
class Base {
    static int a1 = 10;
    static int a2 ;

    static{
        /* static blocks */
        System.out.println("Base class: Static block "+a1+" "+a2);
        // illegal forward reference for a3
        a2 = 10;
        a3 = 20;
    }
    static  final int a3;
    static{
        /* static blocks */
        System.out.println("Base class: Static block "+a1+" "+a2+" "+a3);
    }
    {
        System.out.println("   called for every object");
    }
    public Base(){
        System.out.println("default constructor");
    }
    public Base(int x){
        this(); // calls no argument constructor
        System.out.println("Base class: x is "+x);
    }
}

class Derived extends Base{
    static {
        System.out.println("Derived class: static block");
    }
    public Derived(int x, int y){
        this(x);
        System.out.println("x and y are "+x+" "+y);
    }
    public Derived(int x){
        super(x);
        System.out.println("x is "+x);
    }
}

class A {
    static { System.out.println("A static"); }
    { System.out.println("A instance"); }
    public A() { System.out.println("A constructor"); }
}
class B extends A {
    static { System.out.println("B static"); }
    { System.out.println("B instance"); }
    public B() { System.out.println("B constructor"); }
}
public class Main {
    /*
     * static blocks of base, derive
     * instance intializer of base
     * constructor of base
     * instance intializer of derive
     * constructor of derived
     */
    public static void main(String[] args) {
        Base base1 = new Derived(3,4);
        // Base base2 = new Derived(3,4);
        // base1 = null;
        // base2 = null;
        // System.gc();
        // again static block is not called!
        base1 = new Derived(10){
            public int a = 10;
            {
                System.out.println("Anonymous class intializer  "+a);
                find(this.a);
            }
            static int find(int x){
                return x;
            }
        };

        System.out.println(base1);
        // A obj = new B();
        // Base base2 = new Derived();
    }
}
