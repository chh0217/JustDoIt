package ch.order;

import ch.order.fsm.OrderFsmEngine;
import ch.springmvc.po.MonitorTestDTO;
import ch.springmvc.service.eventListener.EventListenerTestService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午3:05
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource
    private OrderFsmEngine orderFsmEngine;

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String client() {
//        OrderStateEvent orderStateEvent;
//        orderFsmEngine.sendEvent();
        return "测试";
    }
}