package ch.springmvc.deferredresult.dr;

import ch.springmvc.deferredresult.ResponseMsg;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:47
 * @description
 */
@Slf4j
@Component
public class TaskQueue {


    private static final int QUEUE_LENGTH = 10;

    private BlockingQueue<Task> queue = new LinkedBlockingDeque<>(QUEUE_LENGTH);

    private int taskId = 0;


    /**
     * 加入任务
     */
    public void put(DeferredResult<ResponseMsg<String>> deferredResult) {

        taskId++;

        log.info("任务加入队列，id为：{}", taskId);

        queue.offer(new Task(taskId, deferredResult));

    }

    /**
     * 获取任务
     */
    public Task take() throws InterruptedException {

        Task task = queue.poll();

        log.info("获得任务:{}", task);

        return task;
    }
}
