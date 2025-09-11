package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;

@Description(
        name = "mask_from_sl",
        value = "mask_from_sl(string data, int x, int y, int l[, char a]) - 遮盖字符串从位置x到位置y的字符，使用指定长度L的遮盖字符（默认为*）替换",
        extended = "示例：mask_from_sl('浙江省杭州市西湖区胡同口', 2, 3, 3, '*') 返回 '浙***杭州市西湖区胡同口'"
)
public class _017_MaskFromSl extends UDF {
    
    /**
     * 遮盖字符串从位置x到位置y的字符，使用指定长度L的遮盖字符（默认为*）替换
     * 
     * @param data 输入字符串
     * @param x 开始遮盖的位置（从1开始计数）
     * @param y 结束遮盖的位置（从1开始计数）
     * @param l 遮盖字符的长度
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int x, int y, int l) {
        return evaluate(data, x, y, l, '*');
    }
    
    /**
     * 遮盖字符串从位置x到位置y的字符，使用指定长度L的遮盖字符a替换
     * 
     * @param data 输入字符串
     * @param x 开始遮盖的位置（从1开始计数）
     * @param y 结束遮盖的位置（从1开始计数）
     * @param l 遮盖字符的长度
     * @param a 遮盖字符
     * @return 遮盖后的字符串
     */
    public String evaluate(String data, int x, int y, int l, char a) {
        // 处理空值情况
        if (data == null) {
            return null;
        }
        
        // 处理空字符串情况
        if (data.isEmpty()) {
            return data;
        }
        
        // 参数校验和调整
        // 将1-based索引转换为0-based索引
        x = Math.max(1, x) - 1;
        y = Math.max(1, y) - 1;
        
        // 确保x <= y
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        
        // 确保x和y在有效范围内
        int length = data.length();
        x = Math.min(x, length - 1);
        y = Math.min(y, length - 1);
        
        // 确保l不为负数
        if (l < 0) l = 0;
        
        // 构建遮盖字符串
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < l; i++) {
            mask.append(a);
        }
        
        // 构建结果字符串
        StringBuilder result = new StringBuilder();
        
        // 添加前面未遮盖部分
        if (x > 0) {
            result.append(data.substring(0, x));
        }
        
        // 添加遮盖部分
        result.append(mask);
        
        // 添加后面未遮盖部分
        if (y < length - 1) {
            result.append(data.substring(y + 1));
        }
        
        return result.toString();
    }
    
    // 支持BytesWritable类型输入
    public String evaluate(BytesWritable data, int x, int y, int l) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), x, y, l);
    }
    
    public String evaluate(BytesWritable data, int x, int y, int l, char a) {
        if (data == null) {
            return null;
        }
        return evaluate(new String(data.getBytes(), 0, data.getLength()), x, y, l, a);
    }
}
