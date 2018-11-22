package jdk.jdk8;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Stream {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        Proxy<Stream> streamProxy = new Proxy<>(Stream.class);
        int count = (int) streamProxy.invoke("getCountEmptyStringUsingJava7", strings);
        System.out.println(count);

        System.out.println(strings.stream().filter(string->string.isEmpty()).count());

        strings.forEach(System.out::println);
    }


    static class Proxy<T>{
        private T obj;
        private Class clazz;
        private Map<String, Method> methods = new HashMap();
        public Proxy(Class<T> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            obj = (T) Class.forName(clazz.getName()).newInstance();
            for (Method method : clazz.getDeclaredMethods()) {
                methods.put(method.getName(), method);
            }
            this.clazz = clazz;
        }
        public Object invoke(String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            /*int paramLength = args.length;
            Class<?>[] classes = new Class[paramLength];

            for (int i = 0; i < paramLength;i++) {
                classes[i] = args[i].getClass().getDeclaringClass().getSuperclass();
            }
            Method method = clazz.getMethod(methodName, classes);*/
            // 笨办法，接下来一定改进
            Method method = methods.get(methodName);
            long start = System.currentTimeMillis();
            Object result = method.invoke(obj, args);
            System.out.println(String.format("%s cost:%s ms.",methodName, System.currentTimeMillis()-start));
            return result;
        }
    }


    public static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
