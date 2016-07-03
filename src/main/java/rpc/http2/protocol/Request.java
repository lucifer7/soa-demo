package rpc.http2.protocol;

import lombok.Data;

/**
 * Created by lucifer on 2016-7-3.
 */
@Data
public class Request {
    private byte encode;            /* 协议编码 */
    private String command;         /* 命令 */
    private int commandLength;      /* 命令长度 */

    public Request(byte encode, String command, int commandLength) {
        this.encode = encode;
        this.command = command;
        this.commandLength = commandLength;
    }
}
