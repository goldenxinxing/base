package my.rmi.client;

import my.rmi.inter.IHello;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Hello_RMI_Client {
    public static void main(String[] args) {
        try {
            IHello hello = (IHello) Naming.lookup("rmi://localhost:1099/hello");
            System.out.println(hello.sayHello("高新星"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        /*try {
            //获得运行rmiregistry服务的主机上的注 册表
            Registry registry = LocateRegistry.getRegistry(host);
            //查询并获得远程对象的存根
            MyRmiInterface stub = (MyRmiInterface) registry.lookup("Hello");
            //像在使用本地对象方法那样，调用远程方法
            String response = stub.sayHello();
            System.out.println("response:" + response);
        } catch (Exception e) {
            System.out.println("Client exception :" + e.toString());
            e.printStackTrace();
        }*/


    }
}
