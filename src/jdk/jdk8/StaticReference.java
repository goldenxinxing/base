package jdk.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class StaticReference {

    // 这个参数就是为了在使用时再实现接口。而@FunctionInterface是应用函数式接口的标志，应用它之后，接口只能有一个方法定义
    public static <T> String getStr(List<T> p, FuncInterDemo<? super T> funcInterDemo){
        return funcInterDemo.toString(p.get(0));
    }
    public static void main(String[] args){
        List<String> params = new ArrayList<String>(){{add("gxx");}};
        // lambda表达式写法需要看该函数接口的定义，才能确定
        System.out.println(StaticReference.getStr(params, a -> a+"!haha"));
    }
}
