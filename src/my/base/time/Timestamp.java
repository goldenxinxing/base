package my.base.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package: my.base.time<br>
 * @ClassName: Timestamp.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class Timestamp {
    public static void main(String[] args) throws ParseException {
        // Date中存储的long即为时间戳，与时区无关，需要转换时，根据时区再进行转换，所以同一个时间戳，在不同时区下，值不一样！！！
        Date now = new Date();
        System.out.println(now.getTime());
        Date epoch = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/1970 01:00:00");
        System.out.println(epoch.getTime());

    }
}
