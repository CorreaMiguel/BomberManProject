package me.augustojosedev.eventnet.security;

public class Encoder {

    private final int min;
    private final int max;
    private final int period;
    private final int[] key;
    private int index = 0;
    private int buffer = 0;

    public Encoder(int[] key, int min, int max) {
        this.min = min;
        this.max = max;
        period = max + 1 - min;
        this.key = key;
    }

    public Encoder(int[] key) {
        this.min = 0;
        this.max = 255;
        period = max + 1 - min;
        this.key = key;
    }

    public byte[] encode(byte[] values) {
        byte[] newValues = new byte[values.length];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = (byte) encode(newValues[i]);
        }
        return newValues;
    }

    public int[] encode(int[] values) {
        int[] newValues = new int[values.length];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = encode(newValues[i]);
        }
        return newValues;
    }

    public String encode(String string) {
        byte[] bytes = new byte[string.getBytes().length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) encode(string.getBytes()[i]);
        }
        return new String(bytes);
    }

    public int encode(int b) {
        if (b > max || b < min) {
            return b;
        }
        int r = (int) (((b - min) + buffer + key[index = (index + 1) % key.length]) % period);
        buffer = b;
        while (r < 0) {
            r += period;
        }
        return r + min;
    }

    public void reset() {
        buffer = 0;
        index = 0;
    }

}
