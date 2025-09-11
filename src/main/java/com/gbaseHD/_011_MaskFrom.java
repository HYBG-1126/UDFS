package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：遮盖自x至y
 * 示例: x=1，y=2，浙江省杭州市西湖区胡同口→**省杭州市西湖区胡同口
 */
public class _011_MaskFrom extends UDF {

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

        x -= 1;
        y = x + y;

        // 调整参数范围
        if (x < 0) x = 0;
        if (y > strLength) y = strLength;

        // 如果x和y都超出范围，返回原字符串
        if (x >= strLength) {
            return data;
        }

        // 构建结果
        StringBuilder result = new StringBuilder(data);

        // 替换指定范围为掩码
        for (int i = x; i < y; i++) {
            result.setCharAt(i, maskChar.charAt(0));
        }

        return result.toString();
    }
}