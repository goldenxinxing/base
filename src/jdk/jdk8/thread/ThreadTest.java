package jdk.jdk8.thread;

public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5;i++){
            new Thread(new LiftOff(i)).start();
        }
    }
}
class LiftOff implements Runnable{
    private final int index;
    private int count = 10;
    public LiftOff(int i) {
        index=i;
    }
    @Override
    public void run() {
        while (count-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }
    public String status() {
        return "#"+ index + "(" + (count > 0 ? count : "liftOff") + ")";
    }
}