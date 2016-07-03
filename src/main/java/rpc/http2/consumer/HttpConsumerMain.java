package rpc.http2.consumer;

import java.io.IOException;

/**
 * Created by lucifer on 2016-7-3.
 */
public class HttpConsumerMain {
    public static void main(String[] args) {
        try {
            new HttpConsumer().consume();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
