package ch.order.annotation;

import ch.order.enums.OrderEventEnum;
import ch.order.enums.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author 云岩
 * @description 状态机引擎的处理器注解标识
 * @date 2021/5/25 下午1:53
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface OrderProcessor {
    /**
     * 指定状态，state不能同时存在
     */
    OrderStateEnum[] state() default {};

    /**
     * 业务
     */
    String[] bizCode() default {};

    /**
     * 场景
     */
    String[] sceneId() default {};
    /**
     * 订单操作事件
     */
    OrderEventEnum event();
}
