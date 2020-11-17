package com.jmlt.kaifa.entity;
import lombok.Data;
import java.io.Serializable;
@Data
public class UserInfo {
    private Integer id;
    private String serialNumber;
    private String confirmTime;//同意时间
    private String isTarget;//是否目标用户
    private String isConsent;//是否同意
}
