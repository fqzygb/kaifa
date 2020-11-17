package com.jmlt.kaifa.entity;
import lombok.Data;
import java.io.Serializable;
@Data
public class Msg {
    //标识 1表示成功  0表示失败
    private String flg;
    //提示内容
    private String msgContent;

}
