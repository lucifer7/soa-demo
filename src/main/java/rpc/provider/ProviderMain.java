package rpc.provider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lucifer on 2016-7-3.
 */
public class ProviderMain {
    public static void main(String[] args) {
        try {
            new Provider().produce();
        } catch (IOException|ClassNotFoundException|NoSuchMethodException|InvocationTargetException|IllegalAccessException|InstantiationException e) {
            e.printStackTrace();
        }
    }
}
