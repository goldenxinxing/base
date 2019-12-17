package jdk.jdk8.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: jdk.jdk8.concurrent<br>
 * @ClassName: IntTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class IntTest {
  public int index = 0;
  public synchronized void add() {
    index ++;
  }
   public  static void main(String[] args) {
    IntTest test = new IntTest();
     List<Thread> threads = new ArrayList<>();
    for(int i = 0; i < 10000; i++) {
      Thread t = new Thread(() -> {
          test.add();
      });
      t.start();
//      threads.add(t);
    }
//    threads.forEach(thread -> {
//      try {
//        thread.join();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });

     try {
       Thread.sleep(5000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     System.out.println(test.index);
  }
}
