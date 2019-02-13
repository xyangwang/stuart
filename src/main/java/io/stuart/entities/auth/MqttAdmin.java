package io.stuart.entities.auth;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttAdmin implements Serializable {

    private static final long serialVersionUID = -8568892690716127515L;

    @QuerySqlField(index = true, inlineSize = -1)
    private String account;

    @QuerySqlField
    private String password;

    private String desc;

    private String createAccount;

    @QuerySqlField
    private long createTime;

    private String updateAccount;

    private long updateTime;

}
