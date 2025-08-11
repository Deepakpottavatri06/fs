/*only public and default access specifiers for classes.
 * protected and private is not allowed
 */

class A{
    public void f(){
        System.out.println("A.f is called");
    }
    public void call(){
        f();
    }
}

class B extends A{
    @Override
    public void f(){
        System.out.println("B.f is called");
    }
    public void call(){
        f();
    }
}
public class AccessModifier{
    public static void main(String[] args) {
        
        A x = new B();
        x.call();
    }
}