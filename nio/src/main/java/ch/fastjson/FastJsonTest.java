package ch.fastjson;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import java.util.Objects;

/**
 * @author chenhang
 * @date 2020/5/12 上午10:57
 * @description
 */
public class FastJsonTest {

    public static void main(String[] args) {
//        System.out.println(null);
        System.out.println(JSON.toJSONString(null));
        System.out.println(Objects.isNull(JSON.toJSONString(null)));


        System.out.println( Strings.isNullOrEmpty(JSON.toJSONString(null)));
//        System.out.println( Strings.isEmpty(JSON.toJSONString(null)));
//        System.out.println( Strings.is(JSON.toJSONString(null)));
    }


}
