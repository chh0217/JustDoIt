package ch.order.util;

import lombok.Data;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午2:47
 */
public enum ErrorCodeEnum {

    NOT_FOUND_PROCESSOR("没有找到处理器"),
    ORDER_STATE_NOT_MATCH("订单状态不符"),
    ORDER_NOT_FOUND("订单不存在"),
    FOUND_MORE_PROCESSOR("存在多个处理器"),
    ;

    private String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
