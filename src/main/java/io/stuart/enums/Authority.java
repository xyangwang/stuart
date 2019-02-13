package io.stuart.enums;

import org.apache.commons.lang3.StringUtils;

import io.stuart.consts.AclConst;

public enum Authority {

    Deny(0), Allow(1);

    private final int value;

    Authority(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Authority valueOf(int value) {
        switch (value) {
        case 0:
            return Deny;
        case 1:
            return Allow;
        default:
            return null;
        }
    }

    public static Authority strOf(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        switch (value.toLowerCase()) {
        case AclConst.DENY:
            return Deny;
        case AclConst.ALLOW:
            return Allow;
        default:
            return null;
        }
    }

}
