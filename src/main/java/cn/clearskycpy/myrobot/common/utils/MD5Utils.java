package cn.clearskycpy.myrobot.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:  md 加密工具类
 * @author：clearSky
 * @date: 2023/9/15
 * @Copyright： clearskycpy.cn
 */
public class MD5Utils {

    /**
     * MD5加密方法
     * @param input  需要加密的字符串
     * @return 加密后目标字符串
     */
    public static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 encryption algorithm not available.", e);
        }
    }
}
