package io.stuart.entities.param;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryConnections implements Serializable {

    private static final long serialVersionUID = -4840156723356771619L;

    private String clientId;

    private int pageNum;

    private int pageSize;

}
