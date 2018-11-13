package my.serializable.json.test;
import com.alibaba.fastjson.JSONObject;
public class ToObject {
    public static void main(String[] args){
        String json = "{\n" +
                "\t\"defaultText\":\"moren\",\n" +
                "\t\"versionControl\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"versions\":\"1.1.0,1.2.0,1.2.1,1.6.0\",\n" +
                "\t\t\t\"text\":\"low version\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"versions\":\"1.7.0,1.7.1,1.8.1,1.9.0\",\n" +
                "\t\t\t\"text\":\"high version\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        Config config = JSONObject.parseObject(json, Config.class);
        System.out.println(json);
        System.out.println(String.format("1.9.0 text:%s",config.obtainText("1.9.0")));
        System.out.println(String.format("1.9.1 text:%s",config.obtainText("1.9.1")));
    }
}
