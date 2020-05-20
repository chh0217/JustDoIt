import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhang
 * @date 2020/4/22 下午3:45
 * @description
 */
public class Test {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap(nums.length);
        for(int i=0;i < nums.length;i++){
            int another = target - nums[i];
//            Integer index = map.get(another);
            if(map.containsKey(another)){
                return new int[]{i,map.get(another)};
            }
            map.put(nums[i],i);
        }
        return null;

//        int volume = 2048; //100000000000
//        int bitMode = volume - 1;//011111111111
//        int[] result = new int[volume];//store index+1, in order to skip default 0
//
//        for (int i = 0; i < nums.length; i++) {
//            int c = (target - nums[i]) &bitMode;
//            if (result[c] != 0) {
//                return new int[]{result[c] - 1, i};
//            }
//            result[nums[i] & bitMode] = i + 1;
//        }
//        return null;
    }

    public static void main(String[] args) {
        int[] a = twoSum(new int[]{0, 4, 3,0}, 0);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
