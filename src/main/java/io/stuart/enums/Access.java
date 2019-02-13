package io.stuart.enums;

import org.apache.commons.lang3.StringUtils;

import io.stuart.consts.AclConst;

public enum Access {

    Sub(1), Pub(2), SubPub(3);

    private final int value;

    Access(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Access valueOf(int value) {
        switch (value) {
        case 1:
            return Sub;
        case 2:
            return Pub;
        case 3:
            return SubPub;
        default:
            return null;
        }
    }

    public static Access strOf(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        switch (value.toLowerCase()) {
        case AclConst.SUB:
            return Sub;
        case AclConst.PUB:
            return Pub;
        case AclConst.SUBPUB:
            return SubPub;
        default:
            return null;
        }
    }

}
