package com.jmlt.kaifa.entity;
import lombok.Data;
import java.io.Serializable;
@Data
public class VisitLog {
    private Integer ipId;
    private String clientIp;//客户端ip地址
    private String serialNumber;//用户
    private String visitTime;//点击链接时间

}
