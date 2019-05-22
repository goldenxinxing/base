package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: TwoHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class TwoHandler implements EventHandler<LongContext> {
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {

        Thread.sleep(10);
        longContext.setSuccess();
        //System.out.println("two exec:" + longContext.toString());
    }
}
