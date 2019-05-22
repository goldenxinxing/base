package my.execute.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import my.execute.disruptor.event.LongContext;
import my.execute.testCompare.executor.ExecutorTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: MainTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        ExecutorTest executorTest = new ExecutorTest();
        // start
        long start = System.currentTimeMillis();
        RingBuffer<LongContext> ringBuffer = executorTest.prepare();
        for (int i = 1000; i > 0; i--) {
            executorTest.disruptorTest(ringBuffer);
        }
        // end
        System.out.println("disruptor:"+(System.currentTimeMillis() - start));

        // start
        long start1 = System.currentTimeMillis();
        for (int i = 1000; i > 0; i--) {
            executorTest.executorTest(executor);
        }
        // end
        System.out.println("ex:"+(System.currentTimeMillis() - start1));

        // start
        long start2 = System.currentTimeMillis();
        for (int i = 1000; i > 0; i--) {
            executorTest.normalExecutorTest(executor);
        }
        // end
        System.out.println("self:"+(System.currentTimeMillis() - start2));


        /* 结果：
        disruptor:61159
        ex:74820
        self:74117
        */

    }
}
