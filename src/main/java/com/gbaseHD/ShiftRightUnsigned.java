package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 按位无符号右移
 * 示例: a=4, b=1, 4转换成二进制为0100，向右移动一位为0010=2
 */
public class ShiftRightUnsigned extends UDF {
    
    public int evaluate(int a, int b) {
        // 执行无符号右移操作
        return a >>> b;
    }
}