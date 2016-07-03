package rpc.simple1.consumer;

import lombok.extern.log4j.Log4j;
import rpc.service.SayHelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import static util.ProjectConstants.HOST_ADDRESS;
import static util.ProjectConstants.SIMPLE_PORT;

/**
 * Created by lucifer
 * Date: 2016-6-16.
 * Time: 22:39
 */
@Log4j
public class Consumer {
    public void consume() throws IOException, NoSuchMethodException, ClassNotFoundException {
        log.info("Consumer is running...");
        // Name for service
        String interfaceName = SayHelloService.class.getName();

        Method method = SayHelloService.class.getMethod("sayHello", String.class);

        Socket socket = new Socket(HOST_ADDRESS, SIMPLE_PORT);

        Object[] arguments = {"hello"};

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        // 将方法名称与参数传到远端
        outputStream.writeUTF(interfaceName);
        outputStream.writeUTF(method.getName());
        outputStream.writeObject(method.getParameterTypes());
        outputStream.writeObject(arguments);

        // 从远端读取方法执行结果
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object result = inputStream.readObject();

        log.info(result.toString());
    }
}
