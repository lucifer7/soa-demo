package rpc.http2.consumer;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by lucifer on 2016-7-3.
 */
public class HttpConsumerMain {
    private static final String HELLO_COMMAND = "HELLO";
    private static final String WRONG_CMD = "magic hello";


    public static void main(String[] args) {
        HttpConsumer consumer = new HttpConsumer();

        try {
            consumer.consume(HELLO_COMMAND);

            SECONDS.sleep(3);

            consumer.consume(WRONG_CMD);
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}
