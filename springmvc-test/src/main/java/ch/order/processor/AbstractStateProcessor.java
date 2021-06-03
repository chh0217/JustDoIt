package ch.order.processor;

import ch.order.ServiceResult;
import ch.order.check.Checkable;
import ch.order.check.CheckerExecutor;
import ch.order.context.StateContext;
import ch.order.enums.OrderStateEnum;
import ch.order.plugin.PluginHandlerExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 云岩
 * @description 状态机处理器模板类
 * @date 2021/5/25 下午2:39
 */
@Component
public abstract class AbstractStateProcessor<T, C> implements StateProcessor<T,C>, StateActionStep<T, C> {

    @Resource
    private CheckerExecutor checkerExecutor;
    @Resource
    private PluginHandlerExecutor<T, C> pluginHandlerExecutor;

    /**
     * 有可能根据 state+event+bizCode+sceneId 信息获取到的是多个状态处理器processor，
     * 有可能确实业务需要单纯依赖bizCode和sceneId两个属性无法有效识别和定位唯一processor，
     * 那么我们这里给业务开一个口、由业务决定从多个处理器中选一个适合当前上下文的，
     * 具体做法是业务processor通过filter方法根据当前context来判断是否符合调用条件。
     * 当然，如果最终经过业务filter之后，还是有多个状态处理器符合条件，那么这里只能抛异常处理了。这个需要在开发时，对状态和多维度处理器有详细规划。
     */
    public abstract boolean filter(StateContext<C> context);

    @Override
    public final ServiceResult<T, C> action(StateContext<C> context) throws Exception {
        ServiceResult<T, C> result = null;
        Checkable checkable = this.getCheckable(context);
        try {
            // 参数校验器
            result = checkerExecutor.serialCheck(checkable.getParamChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 数据准备
            this.prepare(context);
            // 串行校验器
            result = this.check(context);
            if (!result.isSuccess()) {
                return result;
            }
            // 并行校验器
            result = checkerExecutor.parallelCheck(checkable.getAsyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // getNextState不能在prepare前，因为有的nextState是根据prepare中的数据转换而来
            OrderStateEnum nextState = this.getNextState(context);
            // 业务逻辑
            result = this.action(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // 持久化
            result = this.save(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // after
            this.after(context);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
