package my.concurrent.simple.react;

/**
 * @Package: my.concurrent.simple.react<br>
 * @ClassName: OneEvent.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class OneEvent implements Event<MyContext> {
    @Override
    public MyContext deal(MyContext context) {
        context.setName("my");
        context.setIndex(3);
        return context;
    }
}
