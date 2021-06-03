package ch.order.event;

/**
 * @author 云岩
 * @description
 * @date 2021/5/27 上午10:16
 */
public class CreateEvent implements OrderStateEvent {
    @Override
    public String getEventType() {
        return null;
    }

    @Override
    public String getOrderId() {
        return null;
    }

    @Override
    public boolean newCreate() {
        return false;
    }
}
