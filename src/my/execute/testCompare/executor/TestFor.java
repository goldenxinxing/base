package my.execute.testCompare.executor;

import my.execute.disruptor.event.LongContext;

/**
 * @Package: my.execute.testCompare.executor<br>
 * @ClassName: TestFor.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class TestFor {
    public static void main(String[] args){
        long start = System.currentTimeMillis();

        for(int i = 6000000;i >=0; i--){
            LongContext longContext = new LongContext();
            longContext.setIndex(i);
            longContext.setName("gaoxinxing"+i);
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
