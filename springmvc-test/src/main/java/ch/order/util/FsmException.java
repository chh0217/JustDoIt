package ch.order.util;

/**
 * @author 云岩
 * @description
 * @date 2021/5/26 下午2:47
 */
public class FsmException extends Exception{
    /**
     * 异常提示信息
     */
    private final String message;

    public FsmException(ErrorCodeEnum orderStateNotMatch) {
        this.message = orderStateNotMatch.getMessage();
    }
}
