package io.stuart.enums;

public enum Status {

    Stopped(0), Running(1);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Status valueOf(int value) {
        switch (value) {
        case 0:
            return Stopped;
        case 1:
            return Running;
        default:
            return null;
        }
    }

}
