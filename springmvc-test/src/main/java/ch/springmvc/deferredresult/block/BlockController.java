package ch.springmvc.deferredresult.block;

import ch.springmvc.deferredresult.ResponseMsg;
import ch.springmvc.deferredresult.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:13
 * @description
 */
@Slf4j
@RestController
public class BlockController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMsg<String> getResult() {

        log.info("接收请求，开始处理...");
        ResponseMsg<String> result = taskService.getResult();
        log.info("接收任务线程完成并退出");

        return result;

    }
}
