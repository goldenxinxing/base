package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: OneHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class OneHandler implements EventHandler<LongContext> {
    private Logger logger = LoggerFactory.getLogger(OneHandler.class);
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {

        Thread.sleep(10);
        /*for (int i = 10; i > 0; i--)
            Thread.sleep(10);*/
        //logger.info("one exec:{}", longContext.toString());

    }
}
