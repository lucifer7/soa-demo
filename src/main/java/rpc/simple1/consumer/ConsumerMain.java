package rpc.simple1.consumer;

import java.io.IOException;

/**
 * Created by lucifer on 2016-7-3.
 */
public class ConsumerMain {
    public static void main(String[] args) {
        try {
            new Consumer().consume();
        } catch (IOException|NoSuchMethodException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
