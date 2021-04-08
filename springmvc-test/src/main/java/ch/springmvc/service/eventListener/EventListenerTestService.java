package ch.springmvc.service.eventListener;

import ch.springmvc.annotation.MonitorAnnotation;
import ch.springmvc.enums.MonitorEnum;
import ch.springmvc.event.MonitorEvent;
import ch.springmvc.po.MonitorTestDTO;
import ch.springmvc.po.MonitorTestVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 云岩
 * @description
 * @date 2021/4/7 上午11:21
 */
@Service
public class EventListenerTestService {
    @Autowired
    private ApplicationContext context;

    @MonitorAnnotation
    public void methodA() {
        System.out.println("我是methodA_1");
//        context.publishEvent(new MonitorEvent(new Object()));
        System.out.println("我是methodA_2");

    }

    @MonitorAnnotation
    public String methodB(Long a) {
        System.out.println("我是methodB_1");
        System.out.println("我是methodB_2");
        return "我是methodB";
    }


    @Transactional
    @MonitorAnnotation(monitorEnum = MonitorEnum.MONITOR_1)
    public MonitorTestVO methodC(MonitorTestDTO dto) {
        System.out.println("我是methodC_1");
        System.out.println("我是methodC_2");
        MonitorTestVO vo = new MonitorTestVO();
        vo.setResultA("asdfasd");
        vo.setResultListB(Lists.newArrayList(1, 2, 3));
        return vo;
    }
}
