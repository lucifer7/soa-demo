package rpc.http2.protocol;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by lucifer on 2016-7-3.
 * 请求与响应协议处理工具类
 */
public class ProtocolUtil {

    public static Request readRequest(InputStream input) throws IOException {
        Map map = _parseInput(input);

        // 组装请求返回
        Request request = new Request((byte)map.get("encode"), (String)map.get("content"), (int)map.get("length"));

        return request;
    }

    public static Response readResponse(InputStream input) throws IOException {
        Map map = _parseInput(input);

        // 组装响应返回
        Response response = new Response((byte)map.get("encode"), (String)map.get("content"), (int)map.get("length"));

        return response;
    }

    public static void writeRequest(OutputStream output, Request request) throws IOException {
        output.write(request.getEncode());
        //output.write(response.getResponseLength());
        output.write(ByteUtil.int2ByteArray(request.getCommandLength()));
        output.write(request.getCommand().getBytes(Encode.valueOf(request.getEncode())));
        output.flush();
    }

    public static void writeResponse(OutputStream output, Response response) throws IOException {
        output.write(response.getEncode());
        //output.write(response.getResponseLength());
        output.write(ByteUtil.int2ByteArray(response.getResponseLength()));
        output.write(response.getResponse().getBytes(Encode.valueOf(response.getEncode())));
        output.flush();
    }

    private static Map _parseInput(InputStream input) throws IOException {
        // 1. 读取编码
        byte[] encodeByte = new byte[1];
        input.read(encodeByte);
        byte encode = encodeByte[0];

        // 2. 读取命令长度
        byte[] commandLengthByte = new byte[4];
        input.read(commandLengthByte);
        int commandLength = ByteUtil.bytes2int(commandLengthByte);

        // 3. 读取命令
        byte[] commandByte = new byte[commandLength];
        input.read(commandByte);
        String command = new String(commandByte, Encode.valueOf(encode));

        Map map = Maps.newHashMap();
        map.put("encode", encode);
        map.put("content", command);
        map.put("length", commandLength);

        return map;
    }
}
