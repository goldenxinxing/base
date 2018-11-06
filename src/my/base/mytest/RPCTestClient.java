package my.base.mytest;

import my.base.Interface.HelloService;
import my.base.Interface.impl.HelloServiceImpl;
import my.base.client.RPCClient;
import my.base.registry.Server;
import my.base.registry.impl.ServiceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTestClient {

    public static void main(String[] args) throws IOException {
        // 模拟客户端（调用端）
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("mytest"));
        System.out.println(service.sayHi("mytest2"));
    }
}
