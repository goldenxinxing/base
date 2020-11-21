package jdk.jdk8;

import lombok.Data;

public class DevideTest {
    public static void main(String[] args) {
        T t = new T();
        System.out.println(Math.ceil((t.getNum() + 0) * 100 / t.getTotal()));
    }
}
@Data
class T {
    private Integer total = 0;
    private Integer num = 0;
}
