package my.rmi.mock.skeleton;

import my.rmi.mock.inter.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Person_Skeleton extends Thread {
    private Person person;

    public Person_Skeleton(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            while (socket != null) {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String method = (String) inputStream.readObject();


                ObjectOutputStream outputStream = null;
                if (method.equals("age")) {
                    int age = person.getAge();
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeInt(age);
                    outputStream.flush();
                }

                if (method.equals("name")) {
                    String name = person.getName();
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(name);
                    outputStream.flush();
                }
                //if (null != outputStream) outputStream.close();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.exit(0);
        }
    }

}
