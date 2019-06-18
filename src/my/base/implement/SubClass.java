package my.base.implement;

/**
 * @Package: my.base.implement<br>
 * @ClassName: SubClass.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class SubClass extends SuperClass {
    public String name = "sub";
    public static void main(String[] args){
        SubClass subClass = new SubClass();
        subClass.print();
    }
}
