package ch.springmvc.po;

import lombok.Data;

import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午3:01
 */
@Data
public class MonitorDealDTO {
    /**
     * 入参列表
     */
    private List<Object> parameterList;
    /**
     * 返回值
     */
    private Object returnValue;

}
