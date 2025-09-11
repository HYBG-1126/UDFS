package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;

@Description(
        name = "cover_sl",
        value = "cover_sl(string data, int n, int m, int l[, char a]) - 遮盖字符串的前n个和后m个字符，使用指定长度L的遮盖字符（默认为*）替换",
        extended = "示例：cover_sl('浙江省杭州市西湖区胡同口', 1, 2, 3, '*') 返回 '***江省杭州市西湖区胡***'"
)
public class _016_CoverSl extends UDF {
    
    /**
     * 遮盖字符串的前n个和后m个字符，使用指定长度L的遮盖字符（默认为*）替换
     * 
     * @param data 输入字符串
     * @param n 前面需要遮盖的字符数
     * @param m 后面需要遮盖的字符数
     * @param l 遮盖字符的长度
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int n, int m, int l) {
        return evaluate(data, n, m, l, '*');
    }
    
    /**
     * 遮盖字符串的前n个和后m个字符，使用指定长度L的遮盖字符a替换
     * 
     * @param data 输入字符串
     * @param n 前面需要遮盖的字符数
     * @param m 后面需要遮盖的字符数
     * @param l 遮盖字符的长度
     * @param a 遮盖字符
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int n, int m, int l, char a) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        // 处理空字符串情况
        if (data.isEmpty()) {
            return data;
        }
        
        // 参数校验
        if (n < 0) n = 0;
        if (m < 0) m = 0;
        if (l < 0) l = 0;
        
        int length = data.length();
        
        // 如果n+m大于等于字符串长度，则全部遮盖
        if (n + m >= length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l; i++) {
                sb.append(a);
            }
            return sb.toString();
        }
        
        // 构建遮盖字符串
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < l; i++) {
            mask.append(a);
        }
        
        // 构建结果字符串
        StringBuilder result = new StringBuilder();
        
        // 添加前面的遮盖部分
        if (n > 0) {
            result.append(mask);
        }
        
        // 添加中间未遮盖部分
        result.append(data.substring(n, length - m));
        
        // 添加后面的遮盖部分
        if (m > 0) {
            result.append(mask);
        }
        
        return result.toString();
    }
    
    // 支持BytesWritable类型输入
    public String evaluate(BytesWritable data, int n, int m, int l) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), n, m, l);
    }
    
    public String evaluate(BytesWritable data, int n, int m, int l, char a) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), n, m, l, a);
    }
}
