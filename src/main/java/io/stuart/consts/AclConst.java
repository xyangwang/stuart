package io.stuart.consts;

public interface AclConst {

    static final String SUB = "sub";

    static final String PUB = "pub";

    static final String SUBPUB = "subpub";

    static final String DENY = "deny";

    static final String ALLOW = "allow";

    static final String SEPARATOR = "\\|";

    static final String ALL = "$all";

    static final String LOCAL_ADDRESS = "127.0.0.1";

    static final String POUND = "#";

    static final String EQ_PREFIX = "eq(";

    static final String EQ_SUFFIX = ")";

    static final String EQ_POUND = EQ_PREFIX + POUND + EQ_SUFFIX;

    static final String SYS_SLASH_POUND = "$SYS/" + POUND;

}
