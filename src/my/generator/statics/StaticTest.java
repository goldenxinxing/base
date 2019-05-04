package my.generator.statics;

/**
 * @Package: my.generator.statics<br>
 * @ClassName: StaticTest.java<br>
 * @Description: 这个机制了解了
 * @author: gaoxinxing
 */
public class StaticTest<E> {
    public static <T> void get(T tar){

    }
    // 也就是，static的方法不能用类型参数
    // 报错信息展示，static域中不能使用StaticTest.this，说明类型参数是运行时根据实例化的值具体指定的，而不是预先得知
    /*public static void get1(E tar){

    }*/
}
