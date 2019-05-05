package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: One2EndHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class One2EndHandler implements EventHandler<LongContext> {
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {
        System.out.println("one2end exec:" + longContext.toString());
    }
}
