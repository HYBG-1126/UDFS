package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 掩码屏蔽函数：特殊字符前遮盖（针对首次出现该字符）
 * 示例: 123@456@163.com→***@456@163.com
 */
public class _012_MaskFront extends UDF {
    
    // 默认使用@作为特殊字符，*作为掩码字符
    public String evaluate(String data) {
        return evaluate(data, "@");
    }
    
    public String evaluate(String data, String specialChar) {
        return evaluate(data, specialChar, "*");
    }
    
    public String evaluate(String data, String specialChar, String maskChar) {
        // 处理空值情况
        if (data == null || specialChar == null) {
            return null;
        }
        
        // 查找特殊字符首次出现的位置
        int index = data.indexOf(specialChar);
        
        // 如果未找到特殊字符，返回原字符串
        if (index == -1) {
            return data;
        }
        
        // 构建结果
        StringBuilder result = new StringBuilder();
        
        // 前面的掩码部分
        for (int i = 0; i < index; i++) {
            result.append(maskChar);
        }
        
        // 后面保留的部分
        result.append(data.substring(index));
        
        return result.toString();
    }
}