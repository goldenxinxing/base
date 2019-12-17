package my.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Package: my.base<br>
 * @ClassName: MapMergeTest.java<br>
 * @Description: map合并
 * 博客：https://www.baeldung.com/java-merge-maps
 * 源码：https://github.com/eugenp/tutorials/tree/master/java-collections-maps-2
 * @author: gaoxinxing
 */
public class MapMergeTest {

  public static void main(String[] args) {
    String mapStr = "{\"红色\": {\n"
        + "    \"上衣\":{\n"
        + "      \"key\": \"body_dress_upper_color\",\n"
        + "      \"values\": [\"red\"],\n"
        + "      \"detectType\": 1,\n"
        + "      \"queryType\": \"termsQuery\"\n"
        + "    },\n"
        + "    \"上身\":{\n"
        + "      \"key\": \"body_dress_upper_color\",\n"
        + "      \"values\": [\"red\"],\n"
        + "      \"detectType\": 0,\n"
        + "      \"queryType\": \"termsQuery\"\n"
        + "    }\n"
        + "  }}",
        mapStr2 = "{\"红色\": {\n"
            + "    \"下衣\":{\n"
            + "      \"key\": \"body_dress_lower_color\",\n"
            + "      \"values\": [\"red\"],\n"
            + "      \"detectType\": 1,\n"
            + "      \"queryType\": \"termsQuery\"\n"
            + "    },\n"
            + "    \"下身\":{\n"
            + "      \"key\": \"body_dress_lower_color\",\n"
            + "      \"values\": [\"red\"],\n"
            + "      \"detectType\": 0,\n"
            + "      \"queryType\": \"termsQuery\"\n"
            + "    }\n"
            + "  }}";
    JSON mapJson = JSONObject.parseObject(mapStr), mapJson2 = JSONObject.parseObject(mapStr2);
    // map为主动合并，map2为待合并入map的集合
    Map<String, Map<String, Map<String, Object>>> map = mapJson
        .toJavaObject(new TypeReference<Map<String, Map<String, Map<String, Object>>>>() {
        }),
        map2 = mapJson2
            .toJavaObject(new TypeReference<Map<String, Map<String, Map<String, Object>>>>() {
            });
    // 使用map.merge()
    /*map2.forEach((key, value) -> {
      map.merge(key, value, (origin, newVlue) -> {
        origin.putAll(newVlue);
        return origin;
      });
    });*/
    // 使用 Stream.concat()
    /*Stream
        .concat(map.entrySet().stream(), map2.entrySet().stream())
        .collect(Collectors.toMap(
            Map.Entry::getKey, Map.Entry::getValue,
            (origin, newValue) -> {
              origin.putAll(newValue);
              return origin;
            }));*/
    // 使用 Stream.of
    /*Stream
        .of(map, map2)
        .flatMap(m -> m.entrySet().stream())
        .collect(Collectors.toMap(
            Map.Entry::getKey, Map.Entry::getValue,
            (origin, newValue) -> {
              origin.putAll(newValue);
              return origin;
            }));*/
    // 使用Simple Streaming
    map2.entrySet()
        .stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (origin, newValue) -> {
              origin.putAll(newValue);
              return origin;
            }, () -> map // 汇总者
        ));
    // 使用StreamEx
    System.out.println(map.size());
  }
}
