package com.gbaseHD;


import java.util.Random;

/**
 * 字符打乱 UDF 函数
 * 该函数用于将输入字符串的字符顺序随机打乱
 */
public class _034_CharacterScrambling {
    
    /**
     * 打乱字符串字符顺序
     * @param input 输入字符串
     * @return 打乱后的字符串
     */
    public  String evaluate(String input) {
        // 处理空值或空字符串
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        // 将字符串转换为字符数组
        char[] chars = input.toCharArray();
        
        // 创建随机数生成器
        Random random = new Random();
        
        // 使用 Fisher-Yates 洗牌算法打乱字符顺序
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // 交换字符
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        
        // 将字符数组转换回字符串并返回
        return new String(chars);
    }
}