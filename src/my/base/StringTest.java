package my.base;

import java.util.*;

public class StringTest {
    public static void main(String[] args){
        /*List<Map<String, String>> test = new ArrayList<>();
        test.add(new HashMap<String, String>(){{put("nullVal", "null");put("realNull", null);}});
        test.add(new HashMap<String, String>(){{put("nullVal", "null");put("realNull", null);}});
        Collections.sort(test, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                if (Integer.parseInt(String.valueOf(o1.get("realNull1"))) > Integer.parseInt(String.valueOf(o2.get("realNull1")))) {
                    return 1;
                }
                return 0;
            }
        });*/
        /*
         * hashMap中，存null和没有key返回结果都是null
         * String.valueof是获取object的toString结果，如果是null，那么，返回“null”
         */
        String str1 = "123";
        String str2 = "123";
        System.out.println(str1 == str2);
    }
}
