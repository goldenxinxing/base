package my.rmi.server;

import my.rmi.inter.IHello;
import my.rmi.inter.impl.HelloImpl;

import java.rmi.registry.LocateRegistry;

public class HelloServer {
    public static void main(String[] args) {
        try {
            /* 生成stub和skeleton,并返回stub代理引用 */
            IHello hello = new HelloImpl();
            /* 本地创建并启动RMI Service，被创建的Registry服务将在指定的端口上侦听到来的请求
             * 实际上，RMI Service本身也是一个RMI应用，我们也可以从远端获取Registry:
             *     public interface Registry extends Remote;
             *     public static Registry getRegistry(String host, int port) throws RemoteException;
             */
            LocateRegistry.createRegistry(1099);
            /* 将stub代理绑定到Registry服务的URL上 */
            java.rmi.Naming.rebind("rmi://localhost:1099/hello", hello);
            System.out.println("ready");
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*try {
            //实例化远程对象，同时导出了该对象
            MyRmiImpl server = new MyRmiImpl();
            //获得本地RMI注册表对象
            Registry registry = LocateRegistry.getRegistry();
            //在注册表中绑定远程对象
            registry.bind("Hello", server);
            //通告服务端已准备好了
            System.out.println("System already!");
        } catch (RemoteException e) {
            System.out.println("在建立远程连接的情况出现了异 常" + e.getMessage());
            System.out.println(e.toString());
        } catch (AlreadyBoundException e1) {
            System.out.println("在向注册表 中绑定时出现了问题，名字已被绑定了！ "+e1.getMessage());
        }*/


    }
}
