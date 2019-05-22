package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: One1Handler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class One1Handler implements EventHandler<LongContext> {
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {

        Thread.sleep(60);
        //System.out.println("one1 exec:" + longContext.toString());
    }
}
