package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;

@Description(
        name = "mask_back_sl",
        value = "mask_back_sl(string data, int l[, char a]) - 特殊字符后遮盖（针对首次出现该字符）",
        extended = "示例：mask_back_sl('123@456@163.com', 2, '@') 返回 '123@**'"
)
public class _019_MaskBackSl extends UDF {
    
    /**
     * 特殊字符后遮盖（针对首次出现该字符），使用默认特殊字符@和固定遮盖字符*
     * 
     * @param data 输入字符串
     * @param l 遮盖字符的长度
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int l) {
        return evaluate(data, l, '@');
    }
    
    /**
     * 特殊字符后遮盖（针对首次出现该字符），使用指定特殊字符和固定遮盖字符*
     * 
     * @param data 输入字符串
     * @param l 遮盖字符的长度
     * @param a 特殊字符
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int l, char a) {
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
            mask.append('*'); // 固定使用*作为遮盖字符
        }
        
        // 构建结果字符串
        StringBuilder result = new StringBuilder();
        
        // 添加特殊字符及前面的部分
        result.append(data.substring(0, specialCharIndex + 1));
        
        // 添加遮盖部分
        result.append(mask);
        
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
}
