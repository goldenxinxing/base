package my.rmi.mock.inter;

public interface Person {
    public int getAge() throws Throwable;

    public String getName() throws Throwable;

    public void close();
}
