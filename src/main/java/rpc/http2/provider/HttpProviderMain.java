package rpc.http2.provider;

import java.io.IOException;

/**
 * Created by lucifer on 2016-7-3.
 */
public class HttpProviderMain {
    public static void main(String[] args) {
        try {
            new HttpProvider().produce();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
