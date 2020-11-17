package com.jmlt.kaifa.entity;
import lombok.Data;
import java.io.Serializable;
@Data
public class TMsg implements Serializable {
    private Integer id;
    private String message;
}
