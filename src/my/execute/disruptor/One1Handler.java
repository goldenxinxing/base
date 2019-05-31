package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import my.execute.disruptor.event.LongContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: One1Handler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class One1Handler implements EventHandler<LongContext> {
    private Logger logger = LoggerFactory.getLogger(One1Handler.class);
    @Override
    public void onEvent(LongContext longContext, long l, boolean b) throws Exception {

        Thread.sleep(60);
        //logger.info("one1 exec:{}", longContext.toString());
    }
}
