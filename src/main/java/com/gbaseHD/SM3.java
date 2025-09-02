package com.gbaseHD;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.calcite.avatica.org.apache.commons.codec.binary.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * SM3 UDF for Hive
 * 计算输入字符串的SM3哈希值
 * SM3是中国国家密码管理局发布的密码杂凑算法标准
 */

public class SM3 extends UDF {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public String evaluate(byte[] plainString) {
        return SmUtil.sm3(new String(plainString, StandardCharsets.UTF_8));
    }

    public String evaluate(String plainString) {
        return SmUtil.sm3(plainString);
    }


}