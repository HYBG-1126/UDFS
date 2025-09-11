package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：保留自x至y，其余用掩码替换
 * 示例: x=1，y=2，浙江省杭州市西湖区胡同口→浙江**********
 */
public class _009_KeepFrom extends UDF {

    // 默认使用*作为掩码字符
    public String evaluate(String data, int x, int y) {
        return evaluate(data, x, y, "*");
    }

    public String evaluate(String data, int x, int y, String maskChar) {
        // 处理空值情况
        if (data == null) {
            return null;
        }

        int strLength = data.length();

        x = x - 1;

        if (x < 0) x = 0;
        if (y > strLength) y = strLength;
        if (y < x) return data;

        // 如果x和y都超出范围，返回全掩码
        if (x >= strLength) {
            StringBuilder maskedStr = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                maskedStr.append(maskChar);
            }
            return maskedStr.toString();
        }


        StringBuilder res = new StringBuilder();

        for (int i = 0; i < x; i++) {
            res.append(maskChar);
        }

        res.append(data.substring(x, y));

        for (int i = y; i < strLength; i++) {
            res.append(maskChar);
        }
        return res.toString();
    }
}