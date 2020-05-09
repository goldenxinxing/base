package jdk.jdk8;

public class InnerClassTest2 {
    private B chengyuanB = new B();
    public A innerTest(B b, int i) {
        A a = new A(i) {//意思是构造函数只能传，原始抽象类是什么就是什么，创建匿名内部类时不能再做其他事情
            @Override
            void doSomething() { // （一）
                chengyuanB.funcB("chengyuan b");
                chengyuanB = new B();//成员变量可以有权限更改
                b.funcB("param b");
                // b = new B(); 提示必须为final，参数没有权限修改，因为java设计者在做匿名内部类时，（一）时动态扩充了该方法
                // i = 10; 提示必须为final
                System.out.println("param:"+i+",self:"+this.a);
            }
        };
        return a;
    }
    public static void main(String[] args) {
        InnerClassTest2 test2 = new InnerClassTest2();
        A a = test2.innerTest(new B(), 6);
        a.doSomething();
    }
}
abstract class A{
    int a;
    A(int a){
        this.a = a;
    }
    abstract void doSomething();
}
class B {
    void funcB(String info){
        System.out.println(info);
    }
}

/*

package jdk.jdk8;

class B {
    B() {
    }

    void funcB(String var1) {
        System.out.println(var1);
    }
}

package jdk.jdk8;

abstract class A {
    int a;

    A(int var1) {
        this.a = var1;
    }

    abstract void doSomething();
}

package jdk.jdk8;

public class InnerClassTest2 {
    private B chengyuanB = new B();

    public InnerClassTest2() {
    }

    public A innerTest(final B var1, final int var2) {
        A var3 = new A(var2) {
            void doSomething() {
                InnerClassTest2.this.chengyuanB.funcB("chengyuan b");
                InnerClassTest2.this.chengyuanB = new B();
                var1.funcB("param b");
                System.out.println("param:" + var2 + ",self:" + this.a);
            }
        };
        return var3;
    }

    public static void main(String[] var0) {
        InnerClassTest2 var1 = new InnerClassTest2();
        A var2 = var1.innerTest(new B(), 6);
        var2.doSomething();
    }
}

为什么匿名内部类 在使用 局部变量（非成员函数）必须是final，那么得看看java里如何编译匿名内部类的
package jdk.jdk8;

class InnerClassTest2$1 extends A {
    这里扩充了构造器，将外层类对象【】，自身构造参数，局部变量【】作为构造参数传进来
    InnerClassTest2$1(InnerClassTest2 var1, int var2, B var3, int var4) {
        super(var2);
        this.this$0 = var1;
        this.val$b = var3;
        this.val$i = var4;
    }

    void doSomething() {
        InnerClassTest2.access$000(this.this$0).funcB("chengyuan b");
        InnerClassTest2.access$002(this.this$0, new B());
        this.val$b.funcB("param b");
        System.out.println("param:" + this.val$i + ",self:" + this.a);
    }
}
在doSomething过程中，java要求局部变量必须是final，因为构造器里使用this.val&b = var3，因为java
里参数属于值拷贝，这时对于this.val&b的赋值不会同步回原对象（值）
那么问题来了，正常的参数不是也会有这个问题吗，那也没必要非得final啊！
是不是本身机制（闭包？）
*/