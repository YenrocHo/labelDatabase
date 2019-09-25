/**
 * projectName: labelDatabase
 * fileName: ObtainConfig.java
 * packageName: com.fc.aden.common.conf
 * date: 2019-09-22
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: ObtainConfig
 * @packageName: com.fc.aden.common.conf
 * @description: 获取德慧配置信息
 * @data: 2019-09-22
 **/

@Component
@ConfigurationProperties(prefix = "dh.security")
public class ObtainKeyConfig {

    // 安全秘钥
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
