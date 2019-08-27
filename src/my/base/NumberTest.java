package my.base;

/**
 * @Package: my.base<br>
 * @ClassName: NumberTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class NumberTest {
    private int n_16 = 0x123a;
    private int n_8 = 01237;
    private int n_2 = 0b1010;

    public static void main(String... args){
        Integer i1 = 128;
        Integer i2 = 128;
        /*
             由于自动装箱是编译器阶段，所以以上代码会在编译器变成
             Integer i1 = new Integer(128);
             Integer i2 = new Integer(128);
             由于i1和i2指向堆中创建的两个对象所以 i1==i2会输出false
        */
        System.out.println(i1 == i2); // false

        // 请看一下代码
        Integer i3 = 127;
        Integer i4 = 127;
        System.out.println(i3 == i4); // true
        /*
        结果却输出了true，这是由于java虚拟机里面除了字符串常量池还有
        一个常用的数字常量池，其范围是-128~127，所以如果Integer指向
        这个范围内的数字在编译的时候会直接指向常量池中的数字，而不会创
        建新的对象，所以输出为true。
        */

        Integer i5 = new Integer(127);
        Integer i6 = new Integer(127);
        System.out.println(i5 == i6);

    }
}
