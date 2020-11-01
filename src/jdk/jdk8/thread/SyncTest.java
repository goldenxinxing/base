package jdk.jdk8.thread;

/**
 * Created by gaoxinxing on 2020/4/28.
 */
public class SyncTest {
    public static synchronized void static_func(String str) throws InterruptedException {
        while (true) {
            Thread.sleep(5000);
            System.out.println("static func exectuing" + str);
        }
    }
    public synchronized void func(String str) throws InterruptedException {
        while (true) {
            Thread.sleep(5000);
            System.out.println("non static func exectuing" + str);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //
        SyncTest test = new SyncTest();
        SyncTest test2 = new SyncTest();
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1006);
                    //test.func("1");
                    test2.func("1");
//                    test.static_func("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread_2 = new Thread(new Runnable() {
            @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
            @Override
            public void run() {
                try {
                    Thread.sleep(1060);
                    test2.func("2");// 多線程調用非靜態鎖方法，多實例情況下，不會互相鎖
//                    test2.static_func("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread_1.start();
        thread_2.start();


    }
}
