package com.example.fhdemo.jasypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.NoIvGenerator;
import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptTest {
    public static void main(String[] args) {
        //BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();

        //加密所需的salt(盐),自定义
        textEncryptor.setPassword("retail_salt");
        textEncryptor.setIvGenerator(new NoIvGenerator());
        textEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
