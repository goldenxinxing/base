package jdk.jdk8.concurrent;

/**
 * @Package: jdk.jdk8.concurrent<br>
 * @ClassName: DoubleLockTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class DoubleLockTest {
  private DoubleLockTest(){}

  private static SingleInstance singleInstance;

  public static SingleInstance instance() {
    if (singleInstance == null) {
      synchronized (DoubleLockTest.class) {
        if (singleInstance == null) {
          singleInstance = new SingleInstance();
        }
      }
    }
    return singleInstance;
  }

}

class SingleInstance {

}
