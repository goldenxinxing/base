package my.execute.testCompare.handler;

import my.execute.EventHandler;
import my.execute.testCompare.executor.TestContext;

/**
 * @Package: com.megvii.framework.executor.handler<br>
 * @ClassName: AHandler.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class BHandler implements EventHandler<TestContext> {
    @Override
    public void onEvent(TestContext o) {
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
