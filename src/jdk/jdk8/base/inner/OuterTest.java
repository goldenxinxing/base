package jdk.jdk8.base.inner;

import java.util.List;

public class OuterTest {
    private List<String> items;
    static class staticInner {
        private int i;
        void func() {
            // 不能访问非静态的外部类属性
            // items.add("");
        }
    }

    class nonStaticInner {
        void func() {
            items.add("test");
        }
    }

    public static void main(String[] args) {
        // 静态内部类可以直接创建
        OuterTest.staticInner oi = new OuterTest.staticInner();
        // 非静态内部类，必须先持有外部类的实例
        OuterTest.nonStaticInner nonStaticInner = new OuterTest().new nonStaticInner();
    }
}
