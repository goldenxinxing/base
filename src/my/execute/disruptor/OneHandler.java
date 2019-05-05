package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: OneHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class OneHandler implements EventHandler<LongContext> {
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {
        for (int i = 10; i > 0; i--)
            Thread.sleep(10);
        System.out.println("one exec:" + longContext.toString());

    }
}
