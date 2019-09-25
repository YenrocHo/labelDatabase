/**
 * projectName: labelDatabase
 * fileName: EncryptionUtils.java
 * packageName: com.fc.aden.common.security
 * date: 2019-09-23
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.common.security;

import com.fc.aden.common.conf.ObtainKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;

import static sun.security.x509.CertificateX509Key.KEY;


/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: EncryptionUtils
 * @packageName: com.fc.aden.common.security
 * @description:
 * @data: 2019-09-23
 **/
public class EncryptionUtils {

    public static boolean verify(String phone, String timeStamp, String encryption) {

        String key ="sgo9ej2eqrbofsbe5wjfodsv4rfkiyjb";

        String st = MD5TokenUtil.md5(phone + timeStamp + key);

        return st.equals(encryption);
    }


    public static void main(String[] args) {

        String key ="sgo9ej2eqrbofsbe5wjfodsv4rfkiyjb";

        String st = MD5TokenUtil.md5("20190819" + "123" + key);
        System.out.println(st);
    }
}
