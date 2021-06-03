package ch.order.processor;

import ch.order.ServiceResult;
import ch.order.annotation.OrderProcessor;
import ch.order.check.*;
import ch.order.context.CreateOrderContext;
import ch.order.context.StateContext;
import ch.order.enums.OrderEventEnum;
import ch.order.enums.OrderStateEnum;
import ch.order.enums.ServiceType;
import ch.order.event.CreateEvent;
import ch.order.plugin.PluginHandlerable;
import ch.order.plugin.handler.EstimatePricePluginHandler;
import ch.order.plugin.handler.PluginHandler;
import ch.order.pojo.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/5/25 下午3:24
 */
@Slf4j
@OrderProcessor(state = OrderStateEnum.INIT, bizCode = {"CHEAP", "POPULAR"}, sceneId = "H5", event = OrderEventEnum.CREATE)
@Component
public class OrderCreatedProcessor extends AbstractStateProcessor<String, CreateOrderContext> {
    @Resource
    private CreateParamChecker createParamChecker;
    @Resource
    private UserChecker userChecker;
    @Resource
    private UnfinshChecker unfinshChecker;
    @Resource
    private EstimatePricePluginHandler estimatePricePluginHandler;

    /**
     * 订单创建处理器的校验责任链
     */
    @Override
    public Checkable getCheckable(StateContext<CreateOrderContext> context) {
        return new Checkable() {
            @Override
            public List<Checker> getParamChecker() {
                return Arrays.asList(createParamChecker);
            }
            @Override
            public List<Checker> getSyncChecker() {
                return Collections.EMPTY_LIST;
            }
            @Override
            public List<Checker> getAsyncChecker() {
                return Arrays.asList(userChecker, unfinshChecker);
            }
        };
    }

    @Override
    public ServiceResult<String, CreateOrderContext> check(StateContext<CreateOrderContext> context) {
        return null;
    }

    @Override
    public ServiceResult<String, CreateOrderContext> action(OrderStateEnum nextState, StateContext<CreateOrderContext> context) throws Exception {
        CreateEvent createEvent = (CreateEvent) context.getOrderStateEvent();
        // 促销信息信息
        String promtionInfo = this.doPromotion();
        // 订单创建业务处理逻辑...
        return null;
    }

    /**
     * 促销相关扩展点
     */
    protected String doPromotion() {
        return "1";
    }

    @Override
    public OrderStateEnum getNextState(StateContext<CreateOrderContext> context) {
        // if (context.getOrderStateEvent().getEventType().equals("xxx")) {
        //     return OrderStateEnum.INIT;
        //  }
        return OrderStateEnum.NEW;
    }
    @Override
    public ServiceResult<String, CreateOrderContext> save(OrderStateEnum nextState, StateContext<CreateOrderContext> context) throws Exception {
        OrderInfo orderInfo = (OrderInfo) context.getContext().getOrderInfo();
        // 更新状态
        orderInfo.setOrderState(nextState);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        log.info("save BUSINESS order success, userId:{}, orderId:{}", orderInfo.getUserId(), orderInfo.getOrderId());
        return new ServiceResult<>(orderInfo.getOrderId(), "business下单成功");
    }

    @Override
    public void after(StateContext<CreateOrderContext> context) {

    }

    @Override
    public boolean filter(StateContext<CreateOrderContext> context) {
        OrderInfo orderInfo = (OrderInfo) context.getFsmOrder();
        if (orderInfo.getServiceType() == ServiceType.TAKEOFF_CAR) {
            return true;
        }
        return false;
    }

    @Override
    public PluginHandlerable getPluginHandlerable(StateContext<CreateOrderContext> context) {
        return new PluginHandlerable() {
            @Override
            public List<PluginHandler> getSyncPluginHandler() {
                return Arrays.asList(estimatePricePluginHandler);
            }

            @Override
            public List<PluginHandler> getAsyncPluginHandler() {
                return Arrays.asList(estimatePricePluginHandler);
            }
        };
    }
}
