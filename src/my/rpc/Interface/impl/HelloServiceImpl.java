package my.rpc.Interface.impl;

import my.rpc.Interface.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}
