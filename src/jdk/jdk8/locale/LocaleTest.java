package jdk.jdk8.locale;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Package: jdk.jdk8.locale<br>
 * @ClassName: LocaleTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class LocaleTest {
    public static void main(String[] args) {
        Locale l_start = Locale.getDefault();
        Locale locale = new Locale("en", "US");
        Locale l_end = Locale.getDefault();
        System.out.println();

        LocaleTest test = new LocaleTest();
        System.out.println(test.toBundleName("message", Locale.getDefault()));
    }


    public String toBundleName(String baseName, Locale locale) {
        if (locale == Locale.ROOT) {
            return baseName;
        }

        String language = locale.getLanguage();
        String script = locale.getScript();
        String country = locale.getCountry();
        String variant = locale.getVariant();

        if (language == "" && country == "" && variant == "") {
            return baseName;
        }

        StringBuilder sb = new StringBuilder(baseName);
        sb.append('_');
        if (script != "") {
            if (variant != "") {
                sb.append(language).append('_').append(script).append('_').append(country).append('_').append(variant);
            } else if (country != "") {
                sb.append(language).append('_').append(script).append('_').append(country);
            } else {
                sb.append(language).append('_').append(script);
            }
        } else {
            if (variant != "") {
                sb.append(language).append('_').append(country).append('_').append(variant);
            } else if (country != "") {
                sb.append(language).append('_').append(country);
            } else {
                sb.append(language);
            }
        }
        return sb.toString();

    }
}
