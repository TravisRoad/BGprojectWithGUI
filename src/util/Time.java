package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于生成带有时间戳的输出
 *
 * @author Travis
 */
public class Time {
    public static void println(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("[yyyy-MM-dd HH:mm:ss]>");
        Date date = new Date();// 获取当前时间
        System.out.println(sdf.format(date) + " " + str); // 输出已经格式化的现在时间（24小时制)
    }

    public static void main(String[] args) {
        Time.println("hhh");
    }
}
