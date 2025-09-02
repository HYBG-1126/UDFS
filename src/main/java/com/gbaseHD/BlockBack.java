package com.gbaseHD;

public class BlockBack {
    // 默认使用@作为特殊字符的方法
    public String evaluate(String data) {
        // 调用带默认参数的方法，默认特殊字符为@
        return evaluate(data, "@");
    }

    // 支持自定义特殊字符的方法
    public String evaluate(String data, String a) {
        // 处理空值情况
        if (data == null || a == null) {
            return null;
        }

        // 特殊字符为空时直接返回原字符串
        if (data.isEmpty()) {
            return data;
        }

        // 查找特殊字符首次出现的位置
        int index = data.indexOf(a);

        // 如果找到该字符，返回从该位置开始的子串
        if (index != -1) {
            return data.substring(0, index + a.length());
        } else {
            // 如果未找到该字符，返回空字符串
            return "";
        }

    }
}
