package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：保留前n后m，中间用掩码替换
 * 示例: n=1，m=2，浙江省杭州市西湖区胡同口→浙*********同口
 */
public class _008_Mask extends UDF {
    
    // 默认使用*作为掩码字符
    public String evaluate(String data, int preLength, int postLength) {
        return evaluate(data, "*", preLength, postLength);
    }
    
    public String evaluate(String data, String maskString, int preLength, int postLength) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        int strLength = data.length();
        
        // 处理特殊情况
        if (preLength < 0) preLength = 0;
        if (postLength < 0) postLength = 0;
        
        // 如果前后保留长度超过字符串总长度，则不需要掩码
        if (preLength + postLength >= strLength) {
            return data;
        }
        
        // 构建掩码部分
        StringBuilder maskedPart = new StringBuilder();
        for (int i = 0; i < strLength - preLength - postLength; i++) {
            maskedPart.append(maskString);
        }
        
        // 组合前部分、掩码部分和后部分
        return data.substring(0, preLength) + maskedPart.toString() + 
               (postLength > 0 ? data.substring(strLength - postLength) : "");
    }
}