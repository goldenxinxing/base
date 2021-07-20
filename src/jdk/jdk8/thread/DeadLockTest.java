package jdk.jdk8.thread;

public class DeadLockTest {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                sleep();
                System.out.println("线程1, 等lock2");
                synchronized (lock2) {
                    System.out.println("线程1完成");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                sleep();
                System.out.println("线程2, 等lock1");
                synchronized (lock1) {
                    System.out.println("线程2完成");
                }
            }
        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
