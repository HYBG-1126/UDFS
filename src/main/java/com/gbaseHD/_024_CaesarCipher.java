package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 凯撒加密
 * 示例: caesar_cipher('012ABC6789', 1)->123BCD7890
 */
public class _024_CaesarCipher extends UDF {
    
    // 默认偏移量为1
    public String evaluate(String data) {
        return evaluate(data, 1);
    }
    
    public String evaluate(String data, int shiftValue) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        
        // 对每个字符进行偏移
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            
            // 处理数字 (0-9)
            if (c >= '0' && c <= '9') {
                c = (char) ('0' + (c - '0' + shiftValue) % 10);
            }
            // 处理大写字母 (A-Z)
            else if (c >= 'A' && c <= 'Z') {
                c = (char) ('A' + (c - 'A' + shiftValue) % 26);
            }
            // 处理小写字母 (a-z)
            else if (c >= 'a' && c <= 'z') {
                c = (char) ('a' + (c - 'a' + shiftValue) % 26);
            }
            // 其他字符保持不变
            
            result.append(c);
        }
        
        return result.toString();
    }
}