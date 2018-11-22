package jdk.jdk8;

@FunctionalInterface
public interface FuncInterDemo<T> {
    /*default int compare(T a, T b){
        return (int) a;
    }*/
    String toString(T t);

    boolean equals(Object obj);
}
