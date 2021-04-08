package ch.springmvc.deal;

import ch.springmvc.annotation.MonitorAnnotation;
import ch.springmvc.enums.MonitorEnum;
import ch.springmvc.po.MonitorDealDTO;
import ch.springmvc.po.MonitorTestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 下午2:18
 */
@MonitorAnnotation(monitorEnum = MonitorEnum.MONITOR_1)
@Component
public class MonitorDealA implements MonitorDeal {

    @Override
    public void deal(MonitorDealDTO dto) {
        System.out.println();
        System.out.println("我是监控业务处理类A");
        List<Object> list = dto.getParameterList();
        MonitorTestDTO testDTO = (MonitorTestDTO) list.get(0);
        Object result = dto.getReturnValue();
        System.out.println(list);
        System.out.println(result);
    }
}
