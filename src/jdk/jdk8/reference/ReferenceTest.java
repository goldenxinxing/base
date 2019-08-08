package jdk.jdk8.reference;

/**
 * @Package: jdk.jdk8.reference<br>
 * @ClassName: ReferenceTest.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class ReferenceTest {
    public static void main(String[] args) {
        ReferenceTest referenceTest = new ReferenceTest();
        U u= new U();
        u.setI(6);
        System.out.println("origin："+u.toString());
        referenceTest.update(u);
        System.out.println("origin after："+u.toString());
    }

    public void update(U u) {
        System.out.println("in func:"+u.toString());
        u = new U();
        u.setI(9);
        System.out.println("in func after:"+u.toString());
    }

    /*origin：jdk.jdk8.reference.U@5b2133b1
    in func:jdk.jdk8.reference.U@5b2133b1
    in func after:jdk.jdk8.reference.U@72ea2f77
    origin after：jdk.jdk8.reference.U@5b2133b1*/
}
class U{
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}