package ch.springmvc.po;

import lombok.Data;

import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午1:44
 */
@Data
public class MonitorTestDTO {

    private String columnA;

    private Integer columnB;

    private List<Long> columnC;

    private String columnD;
}
