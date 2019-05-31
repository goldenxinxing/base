package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: TwoHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class TwoHandler implements EventHandler<LongContext> {
    private Logger logger = LoggerFactory.getLogger(TwoHandler.class);
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {

        Thread.sleep(10);
        longContext.setSuccess();
        //logger.info("two exec:{}", longContext.toString());
    }
}
