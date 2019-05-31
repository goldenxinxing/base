package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: One2EndHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class One2EndHandler implements EventHandler<LongContext> {
    private Logger logger = LoggerFactory.getLogger(One2EndHandler.class);
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {
        longContext.setIndex(longContext.getIndex()+1);
        Thread.sleep(1000);
        //logger.info("one2end exec:{}", longContext.toString());
    }
}
