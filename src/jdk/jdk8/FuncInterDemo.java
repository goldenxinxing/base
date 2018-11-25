package jdk.jdk8;

@FunctionalInterface
public interface FuncInterDemo<T> {
    default boolean compare(T a, T b){
        return a.equals(b);
    }
    String toString(T t);

    boolean equals(Object obj);
}
