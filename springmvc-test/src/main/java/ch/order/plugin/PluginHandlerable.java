package ch.order.plugin;

import ch.order.plugin.handler.PluginHandler;

import java.util.Collections;
import java.util.List;

/**
 * @author 云岩
 * @description
 * 状态机插件执行器
 * @date 2021/5/27 上午10:48
 */
public interface PluginHandlerable {
    /**
     * 需同步执行的状态检查器
     */
    default List<PluginHandler> getSyncPluginHandler() {
        return Collections.EMPTY_LIST;
    }

    /**
     * 可异步执行的校验器
     */
    default List<PluginHandler> getAsyncPluginHandler() {
        return Collections.EMPTY_LIST;
    }
}
