package jdk.jdk8.locale;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Package: jdk.jdk8.locale<br>
 * @ClassName: NumberFormatTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        // 货币
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.println(Locale.getDefault() + " " + currencyInstance.format(1000000));
        // 数字
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(100.00));
        // 日期
        DateFormat dateFormat = DateFormat.getDateInstance();
        System.out.println(dateFormat.format(new Date()));
    }
}
