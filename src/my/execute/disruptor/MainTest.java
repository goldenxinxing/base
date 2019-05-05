package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: MainTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
//        LongContextFactory longContextFactory = new LongContextFactory();
        int bufferSize = 1024;
        Disruptor<LongContext> disruptor = new Disruptor<LongContext>(LongContext::new, bufferSize, DaemonThreadFactory.INSTANCE);
        EventHandler twoHandler = new TwoHandler();
        disruptor.handleEventsWith(new OneHandler(),new One1Handler()).then(twoHandler);
        disruptor.after(twoHandler).handleEventsWith(new One2EndHandler());
        disruptor.start();

        RingBuffer<LongContext> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event, sequence, buffer) -> {
            event.setIndex(1);
            event.setName("gxx");
        });


    }
}
