package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

public class _002_NumberFloor extends UDF {

    public Long evaluate(int num, int place) {
        // 计算 10 的 place 次方，用于确定要取整的位数
        long divisor = (long) Math.pow(10, place);
        // 先将数字除以 divisor，再向下取整，最后乘以 divisor，实现按位取整
        return (num / divisor) * divisor;
    }
}
