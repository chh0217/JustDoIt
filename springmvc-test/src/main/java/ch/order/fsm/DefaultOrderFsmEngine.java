package ch.order.fsm;

import ch.order.*;
import ch.order.context.StateContext;
import ch.order.event.OrderStateEvent;
import ch.order.processor.AbstractStateProcessor;
import ch.order.processor.StateProcessor;
import ch.order.service.FsmOrderService;
import ch.order.service.StateProcessorRegistry;
import ch.order.util.ErrorCodeEnum;
import ch.order.util.FsmException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 云岩
 * @description
 * （2）状态机引擎运行时阶段：状态机执行引擎实现
 * @date 2021/5/26 下午2:41
 */
@Component
public class DefaultOrderFsmEngine implements OrderFsmEngine {

    @Resource
    private FsmOrderService fsmOrderService;
    @Resource
    private DefaultStateProcessRegistry stateProcessorRegistry;
    @Override
    public <T,C> ServiceResult<T,C> sendEvent(OrderStateEvent orderStateEvent) throws Exception {
        FsmOrder fsmOrder = null;
        // 新建订单
        if (orderStateEvent.newCreate()) {
            // 获取订单信息
            fsmOrder = this.fsmOrderService.getFsmOrder(orderStateEvent.getOrderId());
            if (fsmOrder == null) {
                throw new FsmException(ErrorCodeEnum.ORDER_NOT_FOUND);
            }
        }
        return sendEvent(orderStateEvent, fsmOrder);
    }

    @Override
    public <T,C> ServiceResult<T,C> sendEvent(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) throws Exception {
        // 构造当前事件上下文
        StateContext context = this.getStateContext(orderStateEvent, fsmOrder);
        // 获取当前事件处理器
        StateProcessor<T,?> stateProcessor = this.getStateProcessor(context);
        // 执行处理逻辑
        return stateProcessor.action(context);
    }

    private <T,C> StateProcessor<T, ?> getStateProcessor(StateContext<?> context) throws FsmException {
        OrderStateEvent stateEvent = context.getOrderStateEvent();
        FsmOrder fsmOrder = context.getFsmOrder();
        // 根据状态+事件对象获取所对应的业务处理器集合
        List<AbstractStateProcessor> processorList = stateProcessorRegistry.acquireStateProcess(fsmOrder.getOrderState(),
                stateEvent.getEventType(), fsmOrder.bizCode(), fsmOrder.sceneId());
        if (processorList == null) {
            // 订单状态发生改变
            if (!Objects.isNull(stateEvent.orderState()) && !stateEvent.orderState().equals(fsmOrder.getOrderState())) {
                throw new FsmException(ErrorCodeEnum.ORDER_STATE_NOT_MATCH);
            }
            throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
        }
        List<AbstractStateProcessor> processorResult = new ArrayList<>(processorList.size());
        // 根据上下文获取唯一的业务处理器
        // 因为有可能根据 state+event+bizCode+sceneId 信息获取到的是多个状态处理器processor，有可能确实业务需要单纯依赖bizCode和sceneId两个属性无法有效识别和定位唯一processor，
        // 那么我们这里给业务开一个口、由业务决定从多个处理器中选一个适合当前上下文的，具体做法是业务processor通过filter方法根据当前context来判断是否符合调用条件。
        for (AbstractStateProcessor processor : processorList) {
            if (processor.filter(context)) {
                processorResult.add(processor);
            }
        }
        if (CollectionUtils.isEmpty(processorResult)) {
            throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
        }
        if (processorResult.size() > 1) {
            throw new FsmException(ErrorCodeEnum.FOUND_MORE_PROCESSOR);
        }
        return processorResult.get(0);
    }

    private StateContext<?> getStateContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        StateContext<?> context = new StateContext(orderStateEvent, fsmOrder);
        return context;
    }

}
