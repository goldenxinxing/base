package my.rmi.mock.client;

import my.rmi.mock.inter.Person;
import my.rmi.mock.inter.stub.Person_Stub;

public class PersonClient {
    public static void main(String[] args){
        try {
            Person person = new Person_Stub();
            System.out.println(String.format("姓名：%s，年龄：%s。", person.getName(), person.getAge()));
            person.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
