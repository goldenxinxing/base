package my.rpc.mytest;

import my.rpc.Interface.HelloService;
import my.rpc.Interface.impl.HelloServiceImpl;
import my.rpc.registry.Server;
import my.rpc.client.RPCClient;
import my.rpc.registry.impl.ServiceCenter;
import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String[] args) throws IOException {
        // 模拟服务端（注册中心）
//        new Thread(new Runnable() {
//            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
//        }).start();
    }
}
