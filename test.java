class A {
    static void display() {
        System.out.println("A");
    }
}

class B extends A {
    static void display() {
        System.out.println("B"); // Hides A's method, not overrides
    }
}

public class test {

}
