package ch.order.processor;

import ch.order.ServiceResult;
import ch.order.annotation.OrderProcessor;
import ch.order.check.Checkable;
import ch.order.context.CreateOrderContext;
import ch.order.context.StateContext;
import ch.order.enums.OrderEventEnum;
import ch.order.enums.OrderStateEnum;
import ch.order.plugin.PluginHandlerable;
import ch.order.processor.AbstractStateProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 云岩
 * @description 状态A对应的状态处理器
 * @date 2021/5/25 下午2:45
 */
@OrderProcessor(state = OrderStateEnum.INIT, bizCode = {"CHEAP", "POPULAR"}, sceneId = "H5", event = OrderEventEnum.CREATE)
@Component
public class StateCreateProcessor extends AbstractStateProcessor<String, CreateOrderContext> {
    @Override
    public void prepare(StateContext<CreateOrderContext> context) {

    }

    @Override
    public ServiceResult<String, CreateOrderContext> check(StateContext<CreateOrderContext> context) {
        return null;
    }

    @Override
    public Checkable getCheckable(StateContext<CreateOrderContext> context) {
        return null;
    }

    @Override
    public OrderStateEnum getNextState(StateContext<CreateOrderContext> context) {
        return null;
    }

    @Override
    public ServiceResult<String, CreateOrderContext> action(OrderStateEnum nextState, StateContext<CreateOrderContext> context) throws Exception {
        return null;
    }

    @Override
    public ServiceResult<String, CreateOrderContext> save(OrderStateEnum nextState, StateContext<CreateOrderContext> context) throws Exception {
        return null;
    }

    @Override
    public void after(StateContext<CreateOrderContext> context) {

    }

    @Override
    public boolean filter(StateContext<CreateOrderContext> context) {
        return false;
    }

    @Override
    public PluginHandlerable getPluginHandlerable(StateContext<CreateOrderContext> context) {
        return null;
    }
}
