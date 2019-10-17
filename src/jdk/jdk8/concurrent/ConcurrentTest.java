package jdk.jdk8.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: jdk.jdk8.concurrent<br>
 * @ClassName: ConcurrentTest.java<br>
 * @Description: 测试非线程安全场景下临界资源争用问题
 * @author: gaoxinxing
 */
public class ConcurrentTest {
  public Map<String, String> common = new HashMap<>();

  public void throwIt(){
    throw new RuntimeException("thread-2 throw");
  }

  public static void main(String[] args) {
    ConcurrentTest concurrentTest = new ConcurrentTest();
    concurrentTest.common.put("test", "origin");
    System.out.println("origin:"+concurrentTest.common.get("test"));
    Thread thread_1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1006);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        concurrentTest.common.put("test", "thread-1");
        System.out.println("on 1:"+concurrentTest.common.get("test"));
      }
    });
    Thread thread_2 = new Thread(new Runnable() {
      @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
      @Override
      public void run() {
        try {
          Thread.sleep(1060);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        concurrentTest.common.put("test", "thread-2");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        concurrentTest.throwIt();

      }
    });
    thread_1.start();
    thread_2.start();
    try {
      Thread.sleep(1010);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("after 1:"+concurrentTest.common.get("test"));

    try {
      Thread.sleep(3010);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("after all:"+concurrentTest.common.get("test"));
  }

}
