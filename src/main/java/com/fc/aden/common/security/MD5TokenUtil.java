/**
 * projectName: labelDatabase
 * fileName: MD5TokenUtil.java
 * packageName: com.fc.aden.common.security
 * date: 2019-09-23
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.common.security;

import java.security.MessageDigest;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: MD5TokenUtil
 * @packageName: com.fc.aden.common.security
 * @description:
 * @data: 2019-09-23
 **/
public class MD5TokenUtil {


    private static final String encryModel = "MD5";

    public static void main(String[] args) {

        System.out.println(md5(System.currentTimeMillis() + ""));
    }

    /**
     * 32λmd5. 32位小写md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return encrypt(encryModel, str);
    }

    public static String encrypt(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            StringBuffer sb = new StringBuffer();
            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i] & 0xFF;
                if (b < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
