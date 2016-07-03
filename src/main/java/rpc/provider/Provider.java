package rpc.provider;

import org.apache.log4j.net.SocketServer;
import org.apache.log4j.or.ObjectRenderer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lucifer
 * Date: 2016-6-16.
 * Time: 22:55
 */
public class Provider {
    public void produce() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1234);

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
            Object service = serviceInterfaceClz.getSigners();
        }
    }
}
