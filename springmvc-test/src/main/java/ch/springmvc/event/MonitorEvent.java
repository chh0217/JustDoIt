package ch.springmvc.event;

import ch.springmvc.enums.MonitorEnum;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author 云岩
 * @description
 * @date 2021/4/7 下午2:44
 */
@Data
public class MonitorEvent extends ApplicationEvent {

    private List<Object> parameterList;

    private Object returnValue;

    private MonitorEnum monitorEnum;

    public MonitorEvent(Object source) {
        super(source);
    }

    public MonitorEvent(Object source, MonitorEnum monitorEnum, List<Object> parameterList, Object returnValue) {
        super(source);
        this.parameterList = parameterList;
        this.returnValue = returnValue;
        this.monitorEnum = monitorEnum;
    }
}
