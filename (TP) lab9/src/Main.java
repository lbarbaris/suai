public class Main {

    public static void main(String args[]) {
        Deadlock.Thread1 T1 = new Deadlock.Thread1();
        Deadlock.Thread2 T2 = new Deadlock.Thread2();
        T1.start();
        T2.start();
    }
}