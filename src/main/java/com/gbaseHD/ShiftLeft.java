package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 将整型数字a向左位移b位
 * 示例: a=2, b=1, 2转换成二进制为0010，向左移动一位为0100=4
 */
public class ShiftLeft extends UDF {
    
    public int evaluate(int a, int b) {
        // 执行左移操作
        return a << b;
    }
}