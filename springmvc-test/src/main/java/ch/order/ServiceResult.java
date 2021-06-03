package ch.order;

import lombok.Data;

/**
 * @author 云岩
 * @description
 * @date 2021/5/25 下午2:56
 */
@Data
public class ServiceResult<T,C> {

    public ServiceResult() {

    }

    public ServiceResult(String orderId, String msg) {

    }

    public boolean isSuccess() {
        return false;
    }
}
