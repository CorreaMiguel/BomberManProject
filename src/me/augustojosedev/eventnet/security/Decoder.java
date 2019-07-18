package me.augustojosedev.eventnet.security;

public class Decoder {

    private final int min;
    private final int max;
    private final long period;
    private final int[] key;
    private int index = 0;
    private int buffer = 0;

    public Decoder(int[] key, int min, int max) {
        this.min = min;
        this.max = max;
        period = max + 1 - min;
        this.key = key;
    }

    public Decoder(int[] key) {
        this.min = 0;
        this.max = 255;
        period = max + 1 - min;
        this.key = key;
    }

    public int decode(int b) {
        if (b > max || b < min) {
            return b;
        }
        b = (int) ((b - min) - buffer - key[index = (index + 1) % key.length]);

        while (b < 0) {
            b += period;
        }
        return buffer = b + min;
    }

    public byte[] decode(byte[] values) {
        byte[] newValues = new byte[values.length];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = (byte) decode(newValues[i]);
        }
        return newValues;
    }

    public int[] decode(int[] values) {
        int[] newValues = new int[values.length];
        for (int i = 0; i < newValues.length; i++) {
            newValues[i] = decode(newValues[i]);
        }
        return newValues;
    }

    public String decode(String string) {
        byte[] bytes = new byte[string.getBytes().length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) decode(string.getBytes()[i]);
        }
        return new String(bytes);
    }

    public void reset() {
        buffer = 0;
        index = 0;
    }

}
