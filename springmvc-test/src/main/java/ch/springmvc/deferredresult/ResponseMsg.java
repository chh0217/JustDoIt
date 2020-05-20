package ch.springmvc.deferredresult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenhang
 * @date 2020/3/24 下午5:00
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> {
    private int code;

    private String msg;

    private T data;
}
