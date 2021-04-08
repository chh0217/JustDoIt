package ch.springmvc.controller;

import ch.springmvc.deferredresult.ResponseMsg;
import ch.springmvc.deferredresult.TaskService;
import ch.springmvc.po.MonitorTestDTO;
import ch.springmvc.service.eventListener.EventListenerTestService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 云岩
 * @description
 * @date 2021/4/7 上午11:20
 */
@Slf4j
@RestController
@RequestMapping("/event")
public class EventListenerTestController {

    @Autowired
    private EventListenerTestService eventListenerTestService;

    @RequestMapping(value = "/eventA", method = RequestMethod.GET)
    public String eventA() {
//        eventListenerTestService.methodA();
//        eventListenerTestService.methodB(113L);
        MonitorTestDTO dto = new MonitorTestDTO();
        dto.setColumnA("fffff");
        dto.setColumnB(77);
        dto.setColumnC(Lists.newArrayList(1L, 2L, 3L));
        eventListenerTestService.methodC(dto);
        return "测试";
    }
}
