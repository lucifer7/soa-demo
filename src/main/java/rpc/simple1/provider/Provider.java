package rpc.simple1.provider;

import lombok.extern.log4j.Log4j;
import util.ReflectUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import static util.ProjectConstants.SIMPLE_PORT;

/**
 * Created by lucifer
 * Date: 2016-6-16.
 * Time: 22:55
 */
@Log4j
public class Provider {
    // start provider first
    public void produce() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        log.info("Provider is running...");
        ServerSocket serverSocket = new ServerSocket(SIMPLE_PORT);

        while (true) {
            Socket socket = serverSocket.accept();

            // 从远端获取服务信息
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String interfaceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            Object[] arguments = (Object[]) inputStream.readObject();

            // invoke
            Class serviceInterfaceClz = Class.forName(interfaceName);
            String serviceName = ReflectUtil.getAllAssignedClass(serviceInterfaceClz).get(0).getName();
            Object service = Class.forName(serviceName).newInstance();
            Method method = serviceInterfaceClz.getMethod(methodName, parameterTypes);

            Object result = method.invoke(service, arguments);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        }
    }
}
