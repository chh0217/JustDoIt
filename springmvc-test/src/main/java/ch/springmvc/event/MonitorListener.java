package ch.springmvc.event;

import ch.springmvc.annotation.MonitorAnnotation;
import ch.springmvc.deal.MonitorDeal;
import ch.springmvc.deal.MonitorDealA;
import ch.springmvc.enums.MonitorEnum;
import ch.springmvc.po.MonitorDealDTO;
import ch.springmvc.po.MonitorTestDTO;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 云岩
 * @description
 * @date 2021/4/7 上午11:39
 */
@Component
@Slf4j
public class MonitorListener //implements ApplicationListener<MonitorEvent>
{

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<Integer, MonitorDeal> monitorDealMap = new HashMap<>();

    @Async
    @EventListener
    public void onApplicationEvent(MonitorEvent monitorEvent) {
        if (MapUtils.isEmpty(monitorDealMap)) {
            populateMap();
        }
        if (MapUtils.isEmpty(monitorDealMap)) {
            return;
        }
        MonitorEnum monitorEnum = monitorEvent.getMonitorEnum();
        MonitorDeal monitorDeal = monitorDealMap.get(monitorEnum.getCode());
        if (Objects.isNull(monitorDeal)) {
            return;
        }
        log.info("开启监控 {}", monitorEnum.getDesc());
        MonitorDealDTO dto = new MonitorDealDTO();
        dto.setParameterList(monitorEvent.getParameterList());
        dto.setReturnValue(monitorEvent.getReturnValue());
        monitorDeal.deal(dto);

        //todo  dingding

//        System.out.println("监听到了methodA变动");
//        List<Object> list = monitorEvent.getParameterList();
//        MonitorTestDTO dto = (MonitorTestDTO) list.get(0);
//        System.out.println(dto.toString());
//        dto.getColumnD().toString();//异常测试
//        Object result = monitorEvent.getReturnValue();
//        System.out.println(result.toString());
    }

    /**
     * 组装map
     */
    private synchronized void populateMap() {
        if (MapUtils.isNotEmpty(monitorDealMap)) {
            return;
        }
        Map<String, MonitorDeal> map = applicationContext.getBeansOfType(MonitorDeal.class);
        if (MapUtils.isEmpty(map)) {
            return;
        }
        for (Map.Entry<String, MonitorDeal> c : map.entrySet()) {
            MonitorDeal m = c.getValue();
            MonitorAnnotation anno = m.getClass().getAnnotation(MonitorAnnotation.class);
            if (Objects.nonNull(anno)) {
                monitorDealMap.put(anno.monitorEnum().getCode(), m);
            }
        }
    }
}
