package my.execute.testCompare.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import my.execute.Executor;
import my.execute.disruptor.One1Handler;
import my.execute.disruptor.One2EndHandler;
import my.execute.disruptor.OneHandler;
import my.execute.disruptor.TwoHandler;
import my.execute.disruptor.event.LongContext;
import my.execute.disruptor.event.LongContextFactory;
import my.execute.testCompare.handler.AHandler;
import my.execute.testCompare.handler.BHandler;
import my.execute.testCompare.handler.CHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Package: com.megvii.framework.executor<br>
 * @ClassName: ExecutorTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class ExecutorTest {
    private static volatile boolean[] results = new boolean[]{false, false, false};

    public void normalExecutorTest(ExecutorService executor) {
        results = new boolean[]{false, false, false};
        // start
        //long start = System.currentTimeMillis();
        TestContext e = new TestContext();
        AHandler a = new AHandler();
        BHandler b = new BHandler();
        CHandler c = new CHandler();

        Thread threada = new Thread() {
            @Override
            public void run() {
                a.onEvent(e);
                results[0] = true;
            }
        };
        Thread threadb = new Thread() {
            @Override
            public void run() {
                b.onEvent(e);
                results[1] = true;
            }
        };
        Thread threadc = new Thread() {
            @Override
            public void run() {
                c.onEvent(e);
                results[2] = true;
            }
        };

        executor.submit(threada);
        executor.submit(threadb);
        while (!results[0] || !results[1]) {
        }
        executor.submit(threadc);

        while (!results[0] || !results[1] || !results[2]) {

        }
        // end
        //System.out.println(System.currentTimeMillis() - start);
    }
    public void executorTest(ExecutorService executor) {

        //long start = System.currentTimeMillis();

        Executor<TestContext> sirector = new Executor(executor);
        AHandler a = new AHandler();
        BHandler b = new BHandler();
        CHandler c = new CHandler();
        sirector.begin(a, b);
        sirector.after(a, b).then(c);
        sirector.ready();
        TestContext e = new TestContext();

        TestContext result = sirector.publish(e);

        //System.out.println(System.currentTimeMillis() - start);
    }

    // disruptor 使用时需注意，创建disruptor时的构造参数中的线程池，需要等于handler数，若小于handler数，剩余的handler将不会被分配到线程
    // 原因是，disruptor在start时，会为每个handler分配线程，并一直持有，导致线程池中工作队列的模式失效，永远不会被释放并执行blockquene中等待的任务
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-my-%d").build();
    //ExecutorService pool = new ThreadPoolExecutor(2, 200,1L, TimeUnit.MILLISECONDS, );
    private ExecutorService executorService = new ThreadPoolExecutor(
            3, 100, 3l,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(500),
            new ThreadPoolExecutor.AbortPolicy());
    Disruptor<LongContext> disruptor = new Disruptor<LongContext>(new LongContextFactory(), 32, executorService);
    EventHandler<LongContext> one2 = new One1Handler();
    EventHandler<LongContext> one = new OneHandler();
    EventHandler<LongContext> two = new TwoHandler();



    private Logger logger = LoggerFactory.getLogger(ExecutorTest.class);
    public void disruptorTest() {
        RingBuffer<LongContext> ringBuffer = disruptor.getRingBuffer();
        AtomicReference<LongContext> longContext = new AtomicReference<>();
        ringBuffer.publishEvent((event, sequence, buffer) -> {
            longContext.set(event);
        });
        /*while (!longContext.get().isSuccess()){

        }*/
    }

    public void prepare(){
        disruptor.handleEventsWith(one, one2).then(two);
        disruptor.start();
    }
}
