package ch.springmvc.deferredresult;

import ch.springmvc.deferredresult.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:05
 * @description
 */
@Slf4j
@Service
public class TaskService {

    public ResponseMsg<String> getResult(){
        log.info("任务开始执行，持续等待中...");

        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("任务处理完成");
        return new ResponseMsg<String>(0,"操作成功","success");
    }
}
