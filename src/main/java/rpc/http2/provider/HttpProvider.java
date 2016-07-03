package rpc.http2.provider;

import rpc.http2.protocol.Encode;
import rpc.http2.protocol.ProtocolUtil;
import rpc.http2.protocol.Request;
import rpc.http2.protocol.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static util.ProjectConstants.HTTP_PORT;

/**
 * Created by lucifer on 2016-7-3.
 */
public class HttpProvider {
    public void produce() throws IOException {
        ServerSocket server = new ServerSocket(HTTP_PORT);

        while (true) {
            Socket client = server.accept();

            // 1. 获取请求数据
            InputStream input = client.getInputStream();
            Request request = ProtocolUtil.readRequest(input);

            // 2. 组装响应数据
            String responseStr = handleCommand(request);
            Response response = new Response(Encode.UTF_8.getValue(), responseStr, responseStr.length());

            // 3. 返回响应数据
            OutputStream output = client.getOutputStream();
            ProtocolUtil.writeResponse(output, response);
        }
    }

    private String handleCommand(Request request) {
        if ("HELLO".equals(request.getCommand())) {
            return "hello there.";
        } else {
            return "what r u talking about?";
        }
    }
}
