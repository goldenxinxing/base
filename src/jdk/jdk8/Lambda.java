package jdk.jdk8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lambda {
    public static void main(String[] args){
        List<String> strs = new ArrayList<>();
        Collections.sort(strs, (a, b)-> a.compareTo(b));
    }
}
