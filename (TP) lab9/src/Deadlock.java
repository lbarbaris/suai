public class Deadlock{
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();



    public static class Thread1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: waiting for 2");

                synchronized (Lock2) {
                    System.out.println("Thread 1: lock 1 & 2");
                }
            }
        }
    }
    public static class Thread2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: waiting for 1");

                synchronized (Lock1) {
                    System.out.println("Thread 2: lock 1 & 2");
                }
            }
        }
    }
}

