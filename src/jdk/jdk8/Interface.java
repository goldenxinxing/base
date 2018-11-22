package jdk.jdk8;

public interface Interface {
    public default String getStr(){
        return "gxx";
    }
    public default String str(){
        return "";
    }
    // 接口定义的方法本来就是要求被继承的，与abstract效果一样，所以加上没啥意思
    public abstract String guguga();

    // default这个默认方法只能用在非抽象方法，道理：上面描述+abstract不能实现+default是实现，故矛盾了
    // public default abstract String gaga();

    public static void main(String[] args){
        System.out.println(new Interface() {
            @Override
            public String guguga() {
                return "guguga";
            }
        }.guguga());
    }
}

class InterFaceImpl implements Interface{


    @Override
    public String guguga() {
        return "guguga";
    }

    public static void main(String[] args){

    }
}