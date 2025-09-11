package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;


public class _007_KeepBack extends UDF {

    /**
     * 保留字符串后N位，其余截断
     *
     * @param data 待处理的字符串
     * @param N    要保留的后N位长度
     * @return 保留后N位的字符串
     */
    public String evaluate(String data, int N) {
        // 处理空值情况
        if (data == null) {
            return null;
        }

        int strLength = data.length();

        // 处理特殊情况：N小于等于0时返回空字符串
        if (N <= 0) {
            return "";
        }

        // 当N大于等于字符串长度时，返回原字符串
        if (N >= strLength) {
            return data;
        }

        // 截取后N位并返回
        return data.substring(strLength - N);
    }
}

