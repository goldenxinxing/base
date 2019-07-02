package jdk.jdk8.thread;

import static java.lang.Thread.currentThread;

/**
 * @Package: jdk.jdk8.thread<br>
 * @ClassName: VolatileTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class VolatileTest {
    public static volatile int index = 0;

    public static void increase() {
        index++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            currentThread().getThreadGroup().list();
            System.out.println(Thread.activeCount() + ":" + index);
            // 让出main线程资源
            Thread.yield();
        }
        System.out.println(index);

        /**
         * ide中debug和run方式不同现象，run会死循环，因为activeCount为2
         * 存在main和Monitor Ctrl-Break两个线程
         */
    }
}
