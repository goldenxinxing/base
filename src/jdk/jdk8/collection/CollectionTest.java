package jdk.jdk8.collection;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CollectionTest {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("轨迹回放"));
        users.add(new User("全域追踪"));
        users.add(new User("落脚点分析"));
        // 使用了sort，但是比较方式未知（估计是按英文规则）
        List<User> list = users.stream().sorted(Comparator.comparing(User::getName).reversed()).collect(Collectors.toList());
        System.out.println(list);
        // 比较方式是按具体语言
        users.sort((o1, o2) -> Collator.getInstance(Locale.getDefault()).compare(o1.getName(), o2.getName()));
        System.out.println(users.size());
    }
}
class User {
    User(String name) {
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}