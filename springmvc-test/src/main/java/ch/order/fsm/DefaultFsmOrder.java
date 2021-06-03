package ch.order.fsm;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午3:32
 */
public class DefaultFsmOrder implements FsmOrder{
    @Override
    public String getOrderId() {
        return "11111";
    }

    @Override
    public String getOrderState() {
        return "create";
    }

    @Override
    public String bizCode() {
        return "bizCode-2";
    }

    @Override
    public String sceneId() {
        return "sceneId-3";
    }
}
