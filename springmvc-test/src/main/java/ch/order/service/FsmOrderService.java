package ch.order.service;

import ch.order.fsm.DefaultFsmOrder;
import ch.order.fsm.FsmOrder;
import org.springframework.stereotype.Component;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午3:25
 */
@Component
public class FsmOrderService {

    /**
     * 获取状态机订单
     */
    public FsmOrder getFsmOrder(String orderId){
        return null;
    }
}
