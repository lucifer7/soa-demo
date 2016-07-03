package rpc.http2.protocol;

/**
 * Created by lucifer on 2016-7-3.
 */
public enum Encode {
    UTF_8((byte)1),
    GBK((byte)0);

    private byte value;

    Encode(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static String valueOf(byte value) {
        if (UTF_8.getValue() == value) {
            return "UTF8";
        } else {
            return "GBK";
        }
    }
}
