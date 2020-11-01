package jdk.jdk8.base;



import java.util.*;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(StringTest.lengthOfLongestSubstring("aasdfvaf"));
    }
    public static int lengthOfLongestSubstring(String s) {
        int length = 0;
        Deque<Character> tmp = new ArrayDeque<>();
        for(int i = 0; i < s.length() ; i++){
            Character now = s.charAt(i);
            if(tmp.contains(now)){
                //tmp.clear();
                while(!tmp.getFirst().equals(now)) {
                    tmp.removeFirst();
                }
                tmp.removeFirst();
            }
            tmp.offer(now);
            if(tmp.size() >= length) {
                length = tmp.size();
            }
        }
        return length;
    }
}
