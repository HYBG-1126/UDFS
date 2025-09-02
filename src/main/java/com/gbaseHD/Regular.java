package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Regular extends UDF {
    // 三级区间规整：参数为 value, f1, f2
    public String evaluate(int value, int f1, int f2) {
        if (value < f1) {
            return "低";
        } else if (value < f2) {
            return "中";
        } else {
            return "高";
        }
    }

    // 五级区间规整：参数为 value, f1, f2, f3, f4
    public String evaluate(int value, int f1, int f2, int f3, int f4) {
        if (value < f1) {
            return "一级";
        } else if (value < f2) {
            return "二级";
        } else if (value < f3) {
            return "三级";
        } else if (value < f4) {
            return "四级";
        } else {
            return "五级";
        }
    }
}