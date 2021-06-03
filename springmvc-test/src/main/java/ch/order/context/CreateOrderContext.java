package ch.order.context;

import ch.order.event.OrderStateEvent;
import ch.order.fsm.FsmOrder;
import ch.order.pojo.OrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 云岩
 * @description
 * @date 2021/5/27 上午10:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrderContext<T> extends StateContext<T> {
    private String estimatePriceInfo;
    private OrderInfo orderInfo;

    public CreateOrderContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        super(orderStateEvent, fsmOrder);
    }
}
