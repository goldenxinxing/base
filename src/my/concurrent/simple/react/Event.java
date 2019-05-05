package my.concurrent.simple.react;

/**
 * @Package: my.concurrent.simple.react<br>
 * @ClassName: Event.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public interface Event<T> {
    T deal(T t);
}
