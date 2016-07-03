package rpc.http2.consumer;

import lombok.extern.log4j.Log4j;
import rpc.http2.protocol.Encode;
import rpc.http2.protocol.ProtocolUtil;
import rpc.http2.protocol.Request;
import rpc.http2.protocol.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.ProjectConstants.HOST_ADDRESS;
import static util.ProjectConstants.HTTP_PORT;

/**
 * Created by lucifer on 2016-7-3.
 */
@Log4j
public class HttpConsumer {
    public void consume(String command) throws IOException {
        log.info("Consumer executes on command: " + command);

        // 1. 初始化请求
        Request request = new Request(Encode.UTF_8.getValue(), command, command.length());
        Socket client = new Socket(HOST_ADDRESS, HTTP_PORT);

        // 2. 发送请求
        OutputStream output = client.getOutputStream();
        ProtocolUtil.writeRequest(output, request);

        // 3. 处理响应数据
        InputStream input = client.getInputStream();
        Response response = ProtocolUtil.readResponse(input);

        // 4. 读取响应内容
        log.info(response.getResponse());
    }

}
