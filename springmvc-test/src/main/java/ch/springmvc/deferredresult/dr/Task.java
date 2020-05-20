package ch.springmvc.deferredresult.dr;

import ch.springmvc.deferredresult.ResponseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:46
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private int taskId;

    private DeferredResult<ResponseMsg<String>> taskResult;

    @Override
    public String toString() {
        return "Task{" +
            "taskId=" + taskId +
            ", taskResult" + "{responseMsg=" + taskResult.getResult() + "}" +
            '}';
    }
}
