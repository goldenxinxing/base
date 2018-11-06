package my.rmi.inter;

import java.rmi.Remote;

/**
 * 远程接口
 */
public interface IHello extends Remote {
    public  String sayHello(String name) throws java.rmi.RemoteException;
}
