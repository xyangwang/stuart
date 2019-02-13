package io.stuart.entities.auth;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QueryGroupIndex;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@QueryGroupIndex(name = "mqtt_acl_dx", inlineSize = -1)
public class MqttAcl implements Serializable {

    private static final long serialVersionUID = 8183942094437614770L;

    @QuerySqlField(index = true, inlineSize = -1)
    private long seq;

    @QuerySqlField(groups = { "mqtt_acl_dx" })
    private String target;

    @QuerySqlField(groups = { "mqtt_acl_idx" })
    private int type;

    @QuerySqlField
    private String topic;

    @QuerySqlField
    private int access;

    @QuerySqlField
    private int authority;

    private String createAccount;

    private long createTime;

    private String updateAccount;

    private long updateTime;

}
