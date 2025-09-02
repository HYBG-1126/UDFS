package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFloor extends UDF {
    public String evaluate(String dateStr, String range) throws ParseException {
        SimpleDateFormat sdf;
        if (dateStr.length() <= 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        Date date = sdf.parse(dateStr);
        // 根据不同的粒度进行日期取整
        if ("year".equals(range)) {
            // 取年份，将月份和日期设为 1 月 1 日
            sdf.applyPattern("yyyy-01-01");
        } else if ("month".equals(range)) {
            // 取月份，将日期设为 1 日
            sdf.applyPattern("yyyy-MM-01");
        } else if ("day".equals(range)) {
            // 保持日期，时分秒设为 0
            sdf.applyPattern("yyyy-MM-dd");
        } else if ("hour".equals(range)) {
            // 小时取整，分钟和秒设为 0
            sdf.applyPattern("yyyy-MM-dd HH:00:00");
        } else if ("minute".equals(range)) {
            // 分钟取整，秒设为 0
            sdf.applyPattern("yyyy-MM-dd HH:mm:00");
        } else if ("second".equals(range)) {
            // 秒取整，保持原样（如果是到秒的粒度，这里可以根据实际需求调整，比如如果输入包含时分秒，也可以处理）
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        }
        return sdf.format(date);
    }
}

