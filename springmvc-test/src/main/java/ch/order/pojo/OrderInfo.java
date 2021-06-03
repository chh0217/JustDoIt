package ch.order.pojo;

import ch.order.enums.OrderStateEnum;
import ch.order.enums.ServiceType;
import lombok.Data;

/**
 * @author 云岩
 * @description
 * @date 2021/5/27 上午10:51
 */
@Data
public class OrderInfo {
    private OrderStateEnum orderState;
    private String orderId;
    private String userId;
    private ServiceType serviceType;
}
