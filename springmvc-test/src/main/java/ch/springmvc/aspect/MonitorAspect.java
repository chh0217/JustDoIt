package ch.springmvc.aspect;

import ch.springmvc.annotation.MonitorAnnotation;
import ch.springmvc.event.MonitorEvent;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author 云岩
 * @description
 * @date 2021/4/8 上午9:55
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Slf4j
public class MonitorAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @AfterReturning(value = "@annotation(monitorAnnotation)", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, MonitorAnnotation monitorAnnotation, Object returnValue) {
        try {
            //方法名
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            StringBuilder sb = new StringBuilder();
            if (ArrayUtils.isNotEmpty(argNames)) {
                for (int i = 0; i < argNames.length; i++) {
                    sb.append("参数名称:").append(argNames[i]).append(" 值:").append(null != args[i] ? args[i].toString() : "");
                }
                log.info("监控 目标方法:{} 入参 {}", methodName, sb.toString());
            } else {
                log.info("监控 目标方法:{} 无入参", methodName, sb.toString());
            }
            if (Objects.nonNull(returnValue)) {
                log.info("监控 目标方法:{} 返回结果 {}", methodName, returnValue.toString());
            } else {
                log.info("监控 目标方法:{} 无返回结果", methodName);
            }
            List<Object> parameterList = Lists.newArrayList();
            if (ArrayUtils.isNotEmpty(args)) {
                for (Object a : args) {
                    parameterList.add(a);
                }
            }
            if (Objects.isNull(monitorAnnotation.monitorEnum())) {
                return;
            }
            applicationContext.publishEvent(new MonitorEvent(joinPoint.getTarget(), monitorAnnotation.monitorEnum(), parameterList, returnValue));
        } catch (Exception e) {
            log.error("业务监控异常 {}", Throwables.getStackTraceAsString(e));
        }
    }
}
