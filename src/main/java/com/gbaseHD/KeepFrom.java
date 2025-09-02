package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：保留自x至y，其余用掩码替换
 * 示例: x=1，y=2，浙江省杭州市西湖区胡同口→浙江**********
 */
public class KeepFrom extends UDF {
    
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
        
        // 调整参数范围
        if (x < 0) x = 0;
        if (y < x) y = x;
        if (y >= strLength) y = strLength - 1;
        
        // 如果x和y都超出范围，返回全掩码
        if (x >= strLength) {
            StringBuilder maskedStr = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                maskedStr.append(maskChar);
            }
            return maskedStr.toString();
        }
        
        // 构建掩码部分
        StringBuilder result = new StringBuilder();
        
        // 前面的掩码部分
        for (int i = 0; i < x; i++) {
            result.append(maskChar);
        }
        
        // 保留的部分
        result.append(data.substring(x, y + 1));
        
        // 后面的掩码部分
        for (int i = y + 1; i < strLength; i++) {
            result.append(maskChar);
        }
        
        return result.toString();
    }
}