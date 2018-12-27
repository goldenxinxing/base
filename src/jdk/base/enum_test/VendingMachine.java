package jdk.base.enum_test;

import java.util.EnumMap;

import static jdk.base.enum_test.Input.*;

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);
    private Input[] types;
    Category(Input... types){
        this.types = types;
    }
    private static EnumMap<Input, Category> categories =
            new EnumMap<Input, Category>(Input.class);
    static {
        for(Category c : Category.class.getEnumConstants()){
            for(Input type : c.types){
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input){
        return categories.get(input);
    }

}

// https://blog.csdn.net/fantasyagain/article/details/44806377
public class VendingMachine{

}

