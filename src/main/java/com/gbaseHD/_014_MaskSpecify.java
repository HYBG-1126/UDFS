package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(
        name = "mask_specify",
        value = "保留字符串前n位和后m位，中间用指定字符遮盖指定长度",
        extended = "示例: mask_specify('浙江省杭州市西湖区胡同口', 1, 2, 3, '*') -> '浙***同口'"
)
public class _014_MaskSpecify extends UDF {

    public String evaluate(String data, int front, int back, int middle) {
        return evaluate(data, front, back, middle, "*");
    }

    public String evaluate(String data, int front, int back, int middle, String maskChar) {
        return evaluate(data, Integer.valueOf(front), Integer.valueOf(back), Integer.valueOf(middle), maskChar);
    }


    public String evaluate(String data, Integer front, Integer back, Integer middle) {
        return evaluate(data, front, back, middle, "*");
    }

    public String evaluate(String data, Integer front, Integer back, Integer middle, String maskChar) {
        if (data == null || front == null || back == null || middle == null) {
            return null;
        }

        String maskStr = maskChar != null ? maskChar : "*";


        // 参数验证
        if (front < 0 || back < 0 || middle < 0) {
            return data;
        }

        int totalLen = data.length();

        // 如果字符串长度不足以保留前后部分
        if (totalLen <= front + back || front >= totalLen) {
            return data;
        }

        // 构建结果字符串
        StringBuilder result = new StringBuilder();

        // 添加前部分
        result.append(data.substring(0, front));

        // 添加遮盖部分
        for (int i = 0; i < middle; i++) {
            result.append(maskStr);
        }

        // 添加后部分
        if (back < totalLen) {
            result.append(data.substring(totalLen - back));
        }
        return result.toString();
    }

}
