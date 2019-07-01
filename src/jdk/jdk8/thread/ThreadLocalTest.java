package jdk.jdk8.thread;

/**
 * @Package: jdk.jdk8.thread<br>
 * @ClassName: ThreadLocalTest.java<br>
 * @Description: ThreadLocal虽然是和当前线程绑定的（），但是一个实例绑定一个，即在当前线程中实例化多个的话，各个之间互不打扰
 * 你可以这样理解，每个实例中map的key一致，但是属于不同的容器，所以是没什么关联的
 * @author: gaoxinxing
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        ThreadLocal threadLocal = new ThreadLocal();
        Entity entity = new Entity();
        threadLocal.set(entity);
        Object obj = threadLocal.get();


        ThreadLocal threadLocal1 = new ThreadLocal();
        Object obj1 = threadLocal1.get();
        System.out.println("ok");
    }
}
class Entity{
    String name = "gaoxinxing";
}