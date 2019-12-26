package my.rp;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * @Package: my.rp<br>
 * @ClassName: RxTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
@Slf4j
public class RxTest {

  public static void main(String[] args) {
    Observable switcher = Observable.create(new OnSubscribe<String>() {

      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("On");
        subscriber.onNext("Off");
        subscriber.onNext("On");
        subscriber.onNext("On");
        subscriber.onCompleted();
      }
    });

    Observable switcher2 = Observable.just("On", "Off", "On", "On");

    Observable switcher3 = Observable.from(new String[]{"On", "Off", "On", "On"});

    Subscriber light = new Subscriber<String>() {
      @Override
      public void onCompleted() {
        System.out.println("结束观察");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("观察出错");
      }

      @Override
      public void onNext(String o) {
        System.out.println("该处理" + o);
      }
    };

    switcher2.subscribe(light);


  }
}
