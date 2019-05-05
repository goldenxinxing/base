package my.concurrent.simple.react;

import com.aol.simple.react.stream.simple.SimpleReact;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Package: my.concurrent.simple.react<br>
 * @ClassName: SimpleReactTest.java<br>
 * @Description: https://www.oschina.net/p/simplereact 开源社区
 * https://github.com/aol/cyclops/wiki/A-simple-API,-and-a-Rich-API github wiki
 * https://github.com/aol/cyclops  github源码
 *
 * @author: gaoxinxing
 */
public class SimpleReactTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyContext context = new MyContext();
        OneEvent oneEvent = new OneEvent();
        List<CompletableFuture<MyContext>> futures = new SimpleReact()
                .<MyContext>react(
                        () -> {
                            context.setFirstName();
                            System.out.println(context.toString());
                            return context;
                        },
                        () -> {
                            context.setIndex(1);
                            System.out.println(context.toString());
                            return context;
                        }//,
                        //() -> {context.setSecondName(); System.out.println(context.toString());return context;}
                        )
                .then((a) -> oneEvent.deal(a))
                .then((ctx) -> {ctx.setIndex(ctx.getIndex()*2);return ctx;})
                //.doOnEach()
                .with(ctx -> {/*System.out.println(ctx.toString());*/ return ctx;});
        System.out.println(futures.get(0).get().getIndex());
    }
}

