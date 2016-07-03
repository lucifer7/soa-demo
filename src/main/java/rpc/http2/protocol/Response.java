package rpc.http2.protocol;

import lombok.Data;

/**
 * Created by lucifer on 2016-7-3.
 */
@Data
public class Response {
    private byte encode;            /* 编码 */
    private String response;        /* 响应 */
    private int responseLength;     /* 响应长度 */

    public Response(byte encode, String response, int responseLength) {
        this.encode = encode;
        this.response = response;
        this.responseLength = responseLength;
    }
}
