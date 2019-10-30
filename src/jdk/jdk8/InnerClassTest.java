package jdk.jdk8;

/**
 * @Package: jdk.jdk8<br>
 * @ClassName: InnerClassTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public abstract class InnerClassTest {

  public abstract void doSomething(int num, String str, Integer ref, Temp temp);

  public static void main(String[] args) {
    Temp temp = new Temp();


    InnerClassTest innerClassTest = new InnerClassTest() {
      @Override
      public void doSomething(int num, String str, Integer ref, Temp temp) {
        num = 8;
        str = "8";
        // Integer545
        ref = 8;
        // Integer547
        temp.setTest(8);
        System.out.println(String.format("inner--num,str,ref,temp.test:%d,%s,%d,%d", num, str, ref, temp.getTest()));
      }
    };

    int num = 6;
    String str = "6";
    Integer ref = 6;// Integer545

    innerClassTest.doSomething(num, str, ref, temp);
    // 内部类中ref为547时，该处ref=null(应该时idea的问题 TODO)
    // 出来后，ref变回了 Integer545
    System.out.println(String.format("out--num,str,ref,temp.test:%d,%s,%d,%d", num, str, ref, temp.getTest()));

    /**
     * inner--num,str,ref,temp.test:8,8,8,8
     * out--num,str,ref,temp.test:6,6,6,8
     */
  }
}
class Temp {
  private int test = 0;
  public void setTest(int test) {
    this.test = test;
  }

  public int getTest() {
    return test;
  }
}
