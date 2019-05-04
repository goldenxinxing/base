package my.generator;

/**
 * @Package: my.generator<br>
 * @ClassName: Coffee.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString(){
        return getClass().getSimpleName() + " " + id;
    }

}
