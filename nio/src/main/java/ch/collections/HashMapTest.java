package ch.collections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhang
 * @date 2020/4/26 下午7:48
 * @description
 */
public class HashMapTest {

    public static void main(String[] args)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>(1);
        map.put("hahaha", "hollischuang");
        map.put("hahaha1", "hollischuang");

        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));
    }


}
