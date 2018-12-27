package jdk.base.enum_test;

import java.util.Random;

public enum Input{
    NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100),
    TOOTHPASTE(200),CHIPS(75),SODA(100),SOAP(50),
    ABORT_TRANSACTION(){
        public int amount(){ //
            throw new RuntimeException("abort.amount()");
        }
    },
    STOP{
        public int amount(){
            throw new RuntimeException("shut_down.amount()");
        }
    };
    int value;
    Input(){}
    Input(int value){this.value = value;}
    int amount(){return value;}// TODO 和上面的具体枚举值的实现方法是有关系的，待确定
    static Random rand = new Random(47);
    public static Input randomSelection(){
        return values()[rand.nextInt(values().length - 1)];
    }
}
