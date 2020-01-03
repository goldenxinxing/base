package my.rpc.mytest;

import my.rpc.Interface.HelloService;
import my.rpc.Interface.impl.HelloServiceImpl;
import my.rpc.client.RPCClient;
import my.rpc.registry.Server;
import my.rpc.registry.impl.ServiceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTestClient {

    public static void main(String[] args) throws IOException {
        // 模拟客户端（调用端）
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("192.168.1.15", 8088));
        System.out.println(service.sayHi("mytest"));
        System.out.println(service.sayHi("mytest2"));
    }
}
