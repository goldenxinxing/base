package jdk.jdk8.base.bean;

/**
 * 对基本类型来说，基本类型的参数传递就是值拷贝
 */
public class invokeTest {
    public void baseTypeFunc(int i) {
        i++;
        System.out.println("one before i = "+i);
        secondInvoke secondInvoke = new secondInvoke();
        secondInvoke.baseTypeFunc(i);
        System.out.println("one after i = "+i);
    }

    public static void main(String[] args) {
        invokeTest invokeTest = new invokeTest();
        int i = 1;
        invokeTest.baseTypeFunc(i);
        System.out.println("origin i = "+i);
    }
}
class secondInvoke{
    public void baseTypeFunc(int j) {
        j++;
        System.out.println("two j = "+j);
    }
}