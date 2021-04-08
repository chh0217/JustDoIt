package ch.springmvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午2:08
 */
@AllArgsConstructor
@Getter
public enum MonitorEnum {

    NULL(null,""),
    MONITOR_1(1,"计算总分"),
    MONITOR_2(2,"计算订单数目");

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;
}
