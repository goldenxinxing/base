package my.execute.disruptor.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @Package: my.execute.disruptor<br>
 * @ClassName: LongContext.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class LongContext implements Serializable {
    private int index=0;
    private String name = "gaoxinxing";
    private String number = "201109010328";
    private boolean isSuccess = false;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess() {
        isSuccess = true;
    }

    public String toString(){
        return "index:" + index + ",name:" + name + ",number:" + number;
    }
}
