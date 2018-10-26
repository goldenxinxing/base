package my.base.mytest;

import my.base.Interface.HelloService;
import my.base.Interface.impl.HelloServiceImpl;
import my.base.registry.Server;
import my.base.client.RPCClient;
import my.base.registry.impl.ServiceCenter;
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
