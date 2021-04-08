package ch;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) throws Exception {
//        System.out.println(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
        System.out.println(6690884449050824307L % 64);
//        List<String> nameSlice = Lists.newArrayList("宇", "安", "一次性", "医用", "外科", "口罩", "防疫", "口罩", "现货");
////
//        System.out.println(test(nameSlice, 38));
//        List<HealthPointInfoDTO> xx = Lists.newArrayList();
//        HealthPointInfoDTO d = new HealthPointInfoDTO();
//        d.setItemIds(Lists.newArrayList(1L));
//        d.setShopId(1L);
//        xx.add(d);
//        System.out.println(JSON.toJSONString(xx));
//        DateTime now = DateTime.now();
//        DateTime monday = now.withDayOfWeek(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).minusWeeks(1);
//        DateTime sunday = now.withDayOfWeek(7).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).minusWeeks(1);
//        System.out.println(monday);
//        System.out.println(sunday);

        List<Integer> aList = Lists.newArrayList(1);
        System.out.println(aList.subList(0,1));
    }

    static boolean test(List<String> nameSlice, int length) {
        List<String> newNameSlice = Lists.newArrayList();
        for (String s : nameSlice) {
            if (isAllLetterDigit(s) || s.length() < 2) {
                continue;
            }
            newNameSlice.add(s);
        }
        //切词数量大于等于两字的词少于3，直接过滤，同时切词去除纯字母数字的词
        if (newNameSlice.size() < 3) {
            return false;
        }
        int maxCnt = 0;
        String tmp = "";
        String regex;
        Pattern p;
        Matcher m;
        String toStr = newNameSlice.toString();
        for (String str : newNameSlice) {
            if (tmp.equals(str)) {
                continue;
            }
            tmp = str;
            regex = str;
            p = Pattern.compile(regex);
            m = p.matcher(toStr);
            int cnt = 0;
            while (m.find()) {
                cnt++;
            }
            // 如果叠词出现2次
            if (cnt == 2) {
                double rate = new BigDecimal(str.length() * cnt).divide(new BigDecimal(length), 2, RoundingMode.HALF_UP).doubleValue();
                System.out.println(str.length() * cnt);
                System.out.println("str:" + str.length());
                System.out.println("rate:" + rate);
                if (rate >= 0.9) {
                    return true;
                }
                continue;
            }
            // 如果叠词出现3次及以上
            if (cnt >= 3) {
                double rate = new BigDecimal(str.length() * cnt).divide(new BigDecimal(length)).doubleValue();
                System.out.println("str:" + str);
                System.out.println("rate:" + rate);
                return rate >= 0.8 ? true : false;
            }
        }
        return false;
    }

    static boolean isAllLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }
}
