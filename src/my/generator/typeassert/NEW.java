package my.generator.typeassert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: my.generator.typeassert<br>
 * @ClassName: NEW.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class NEW {
    public static <K, V> Map<K, V> map(){
        return new HashMap<K, V>();
    }

    public static <T> List<T> list(){
        return new ArrayList<T>();
    }

    public static void main(String[] args){
        Map<String, Map<String, Integer>> list = NEW.map();
    }
}
