package ch.order.processor;

import ch.order.ServiceResult;
import ch.order.context.StateContext;

/**
 * @author 云岩
 * @description 状态机处理器接口
 * @date 2021/5/25 下午1:49
 */
public interface StateProcessor<T,C> {
    /**
     * 执行状态迁移的入口
     * @param context
     * @throws Exception
     */
    ServiceResult<T, C> action(StateContext<C> context) throws Exception;
}
