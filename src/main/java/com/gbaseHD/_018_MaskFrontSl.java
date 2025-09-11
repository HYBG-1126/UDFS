package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;

@Description(
        name = "mask_front_sl",
        value = "mask_front_sl(string data, int l[, char a]) - 特殊字符前遮盖（针对首次出现该字符）",
        extended = "示例：mask_front_sl('123@456@163.com', 2, '@') 返回 '**@456@163.com'"
)
public class _018_MaskFrontSl extends UDF {
    
    /**
     * 特殊字符前遮盖（针对首次出现该字符），使用默认特殊字符@和默认遮盖字符*
     * 
     * @param data 输入字符串
     * @param l 遮盖字符的长度
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int l) {
        return evaluate(data, l, '@');
    }
    
    /**
     * 特殊字符前遮盖（针对首次出现该字符），使用指定特殊字符和默认遮盖字符*
     * 
     * @param data 输入字符串
     * @param l 遮盖字符的长度
     * @param a 特殊字符
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int l, char a) {
        return evaluate(data, l, a, '*');
    }
    
    /**
     * 特殊字符前遮盖（针对首次出现该字符），使用指定特殊字符和指定遮盖字符
     * 
     * @param data 输入字符串
     * @param l 遮盖字符的长度
     * @param a 特殊字符
     * @param maskChar 遮盖字符
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int l, char a, char maskChar) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        // 处理空字符串情况
        if (data.isEmpty()) {
            return data;
        }
        
        // 确保l不为负数
        if (l < 0) l = 0;
        
        // 查找特殊字符的位置
        int specialCharIndex = data.indexOf(a);
        
        // 如果特殊字符不存在，则返回原字符串
        if (specialCharIndex == -1) {
            return data;
        }
        
        // 构建遮盖字符串
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < l; i++) {
            mask.append(maskChar);
        }
        
        // 构建结果字符串
        StringBuilder result = new StringBuilder();
        
        // 添加遮盖部分
        result.append(mask);
        
        // 添加特殊字符及后面的部分
        result.append(data.substring(specialCharIndex));
        
        return result.toString();
    }
    
    // 支持BytesWritable类型输入
    public String evaluate(BytesWritable data, int l) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), l);
    }
    
    public String evaluate(BytesWritable data, int l, char a) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), l, a);
    }
    
    public String evaluate(BytesWritable data, int l, char a, char maskChar) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), l, a, maskChar);
    }
}
