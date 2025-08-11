

public class NestedClasses {
    private int secret = 99;
    class Inner{
        private int innerSecret = 7;
        void show(){
            System.out.println(secret);
        }
    }

    static class Nested{
        void show(NestedClasses o){
            System.out.println(o.secret);
        }
    }
    void probe(){
        Inner in = new Inner();
        System.out.println(in.innerSecret);
    }
    void printer(Integer i){
        System.out.println("Int");
    }
    // void printer(long i){
    //     System.out.println("long");
    // }
    void printer(int...i){
        System.out.println("... int");
    }
    public static void main(String[] args) {
        // new NestedClasses().new Inner().show();
        // new Nested().show(new NestedClasses());
        // new Nested().show(new NestedClasses());
        NestedClasses t = new NestedClasses();
        t.printer((short)1);
    }

}
