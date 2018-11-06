package my.rmi.mock.inter.stub;

import my.rmi.mock.inter.Connection;
import my.rmi.mock.inter.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Person_Stub extends Connection implements Person {

    private Socket socket;

    public Person_Stub() throws Throwable {
        // connect to skeleton
        socket = new Socket("localhost", 9000);
    }

    @Override
    public int getAge() {

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        int age = 0;
        try {
            // 在告诉服务端，我要调的方法
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject("age");
            outputStream.flush();
            // 等待返回int
            inputStream = new ObjectInputStream(socket.getInputStream());
            age = inputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return age;
    }

    @Override
    public String getName(){
        ObjectOutputStream outStream = null;
        ObjectInputStream inStream = null;
        String name = null;
        try {
            // pass method name to skeleton
            outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject("name");
            outStream.flush();
            inStream = new ObjectInputStream(socket.getInputStream());
            name = (String) inStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return name;
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
