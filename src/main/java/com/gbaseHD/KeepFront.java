package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class KeepFront extends UDF {
    public String evaluate(String data, int M) {
        // 处理空值情况
        if (data == null) {
            return null;
        }


        // 如果 M 小于等于 0 或者字符串长度小于等于 M，返回原字符串
        if (M <= 0 || data.length() <= M) {
            return data;
        }

        // 截取前 M 位并返回
        return data.substring(0, M);
    }
}
