package ch.order.plugin.annotation;

import ch.order.enums.OrderEventEnum;
import ch.order.enums.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author 云岩
 * @description
 * 插件注解
 * @date 2021/5/27 上午10:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface ProcessorPlugin {
    /**
     * 指定状态，state不能同时存在
     */
    OrderStateEnum[] state() default {};

    /**
     * 订单操作事件
     */
    OrderEventEnum event();

    /**
     * 业务
     */
    String[] bizCode() default {};

    /**
     * 场景
     */
    String[] sceneId() default {};
}
