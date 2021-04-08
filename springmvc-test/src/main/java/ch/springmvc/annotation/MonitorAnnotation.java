package ch.springmvc.annotation;

import ch.springmvc.enums.MonitorEnum;

import java.lang.annotation.*;

/**
 * @author 云岩
 * @description
 * @date 2021/4/7 下午4:27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MonitorAnnotation {
    /**
     * 监控编码
     * @return
     */
    MonitorEnum monitorEnum() default MonitorEnum.NULL;
}
