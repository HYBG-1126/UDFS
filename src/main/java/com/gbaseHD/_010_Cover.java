package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：遮盖前n后m
 * 示例: n=1，m=2，浙江省杭州市西湖区胡同口→*江省杭州市西湖区胡**
 */
public class _010_Cover extends UDF {
    
    // 默认使用*作为掩码字符
    public String evaluate(String data, int n, int m) {
        return evaluate(data, n, m, "*");
    }
    
    public String evaluate(String data, int n, int m, String maskChar) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        int strLength = data.length();
        
        // 处理特殊情况
        if (n < 0) n = 0;
        if (m < 0) m = 0;
        
        // 如果前后遮盖长度超过字符串总长度，则全部遮盖
        if (n + m >= strLength) {
            StringBuilder maskedStr = new StringBuilder();
            for (int i = 0; i < strLength; i++) {
                maskedStr.append(maskChar);
            }
            return maskedStr.toString();
        }
        
        // 构建结果
        StringBuilder result = new StringBuilder();
        
        // 前面的掩码部分
        for (int i = 0; i < n; i++) {
            result.append(maskChar);
        }
        
        // 中间保留的部分
        result.append(data.substring(n, strLength - m));
        
        // 后面的掩码部分
        for (int i = 0; i < m; i++) {
            result.append(maskChar);
        }
        
        return result.toString();
    }
}