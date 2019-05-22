package my.execute.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * @Package: my.execute.disruptor.event<br>
 * @ClassName: LongContextFactory.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class LongContextFactory implements EventFactory<LongContext> {
    @Override
    public LongContext newInstance() {
        LongContext longContext = new LongContext();
        longContext.setIndex(0);
        longContext.setName("gxx");
        return longContext;
    }
}
