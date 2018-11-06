package my.rmi.mock.server;

import my.rmi.mock.inter.Person;
import my.rmi.mock.skeleton.Person_Skeleton;

public class PersonServer implements Person {
    private int age;
    private String name;
    public PersonServer(String name, int age){
        this.age = age;
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }

    @Override
    public void close() {

    }

    public static void main(String args []){
        // new object server
        Person person = new PersonServer("newStar", 26);
        Person_Skeleton skel = new Person_Skeleton(person);
        skel.start();
    }

}
