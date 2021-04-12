package ch.springmvc.deal;

import ch.springmvc.annotation.MonitorAnnotation;
import ch.springmvc.enums.MonitorEnum;
import ch.springmvc.po.MonitorDealDTO;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午2:24
 */
@MonitorAnnotation(monitorEnum = MonitorEnum.MONITOR_2)
public class MonitorDealB implements MonitorDeal {
    @Override
    public String deal(MonitorDealDTO dto) {
        System.out.println("我是业务监控处理类B");
        return null;
    }
}
