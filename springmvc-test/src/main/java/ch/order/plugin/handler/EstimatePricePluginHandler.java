package ch.order.plugin.handler;

import ch.order.ServiceResult;
import ch.order.context.CreateOrderContext;
import ch.order.context.StateContext;
import ch.order.enums.OrderEventEnum;
import ch.order.enums.OrderStateEnum;
import ch.order.plugin.annotation.ProcessorPlugin;
import org.springframework.stereotype.Component;

/**
 * @author 云岩
 * @description
 * 预估价插件
 * @date 2021/5/27 上午10:28
 */
@ProcessorPlugin(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "BUSINESS")
@Component
public class EstimatePricePluginHandler  implements PluginHandler<String, CreateOrderContext>{
    @Override
    public ServiceResult action(StateContext<CreateOrderContext> context) throws Exception {
//        String price = priceSerive.getPrice();
        String price = "";
        context.getContext().setEstimatePriceInfo(price);
        return new ServiceResult();
    }
}
