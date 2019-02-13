package io.stuart.enums;

public enum Target {

    Username(1), IpAddr(2), ClientId(3), All(4);

    private final int value;

    Target(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Target valueOf(int value) {
        switch (value) {
        case 1:
            return Username;
        case 2:
            return IpAddr;
        case 3:
            return ClientId;
        case 4:
            return All;
        default:
            return null;
        }
    }

}
