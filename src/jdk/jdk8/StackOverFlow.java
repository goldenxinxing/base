package jdk.jdk8;

/**
 * @Package: jdk.jdk8<br>
 * @ClassName: StackOverFlow.java<br>
 * @Description: 模拟栈溢出
 * @author: gaoxinxing
 */
public class StackOverFlow {
    public static void main(String[] args) {
        Stack stack = new Stack();
        try {
            stack.stackLeak();
        } catch (Throwable e) {
            System.out.println("stacklenth:" + stack.stackLength);
            throw e;
        }

    }
}

class Stack {
    public int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
}