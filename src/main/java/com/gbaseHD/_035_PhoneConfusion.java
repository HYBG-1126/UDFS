package com.gbaseHD;


import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 手机号混淆 UDF 函数
 * 该函数用于对手机号码进行混淆处理，隐藏中间4位数字
 */
public class _035_PhoneConfusion extends UDF {
    
    /**
     * 手机号混淆处理
     * 将手机号码的中间4位数字替换为星号(*)，保留首尾各3位数字
     * @param phone 输入的手机号码
     * @return 混淆后的手机号码
     */
    public String evaluate(String phone) {
        // 处理空值或null情况
        if (phone == null || phone.isEmpty()) {
            return phone;
        }
        
        // 去除输入中的空格、横线等分隔符
        String cleanedPhone = phone.replaceAll("[\\s]", "");
        
        // 验证是否为有效的11位手机号码
        if (cleanedPhone.length() != 11 ) {
            return phone; // 如果不是有效手机号码，返回原值
        }
        
        // 对手机号码进行混淆：保留前3位和后4位，中间4位用*代替
        return cleanedPhone.substring(0, 5) + (int)((Math.random()*9+1)*100000);


    }

}
