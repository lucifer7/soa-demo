package rpc.service.impl;

import rpc.service.SayHelloService;

/**
 * Created by lucifer
 * Date: 2016-6-16.
 * Time: 21:56
 */
public class SayHelloServiceImpl implements SayHelloService {
    public String sayHello(String arg) {
        if("hello".equals(arg)) {
            return "Hello guys.";
        }
        return "What are you talking about?";
    }
}
