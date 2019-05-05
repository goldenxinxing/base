package my.concurrent.simple.react;

/**
 * @Package: my.concurrent.simple.react<br>
 * @ClassName: MyContext.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class MyContext {
    private int index = 0;
    private String name = "default name";
    private String number = "default number";

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setFirstName() {
        name = "first Name";
    }

    public void setSecondName() {
        name = "second Name";
    }

    public String toString() {
        return "index:" + index + ",name:" + name + ",number:" + number;
    }
}
