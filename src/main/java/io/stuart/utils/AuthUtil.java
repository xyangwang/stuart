package io.stuart.utils;

import org.apache.commons.lang3.StringUtils;

import io.stuart.config.Config;
import io.stuart.consts.AclConst;
import io.stuart.entities.internal.MqttAuthority;
import io.stuart.enums.Access;
import io.stuart.enums.Authority;
import io.stuart.functions.TopicMatchFunction;

public class AuthUtil {

    public static boolean isMatch(String topic, String filter) {
        if (!TopicUtil.validateTopic(topic) || !TopicUtil.validateTopic(filter)) {
            return false;
        }

        boolean result = true;

        if (filter.startsWith(AclConst.EQ_PREFIX) && filter.endsWith(AclConst.EQ_SUFFIX)) {
            int prefixLen = AclConst.EQ_PREFIX.length();
            int suffixLen = AclConst.EQ_SUFFIX.length();
            String eqTopic = filter.substring(prefixLen, filter.length() - suffixLen);

            if (topic.equals(eqTopic)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            if (TopicMatchFunction.match(topic, filter) == 1) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    public static MqttAuthority transform2Authority(String value) {
        MqttAuthority result = null;

        if (StringUtils.isNotBlank(value)) {
            // get split
            String[] split = value.split(AclConst.SEPARATOR);
            // get access
            Access access = Access.strOf(split[0]);
            // get authority
            Authority authority = Authority.strOf(split[1]);

            if (access != null && authority != null) {
                // initialize mqtt authority
                result = new MqttAuthority();
                // set access
                result.setAccess(access);
                // set authority
                result.setAuthority(authority);
            }
        }

        return result;
    }

    public static boolean canAccess(Access access, MqttAuthority authority) {
        if (authority == null) {
            if (Config.isAuthAclAllowNomatch()) {
                return true;
            } else {
                return false;
            }
        }

        // check result
        boolean result = true;

        // get access
        Access dstAccess = authority.getAccess();
        // get authority
        Authority dstAuthority = authority.getAuthority();

        if (Access.Sub == access && (Access.Sub == dstAccess || Access.SubPub == dstAccess) && Authority.Deny == dstAuthority) {
            result = false;
        } else if (Access.Pub == access && (Access.Pub == dstAccess || Access.SubPub == dstAccess) && Authority.Deny == dstAuthority) {
            result = false;
        }

        // return result
        return result;
    }

}
