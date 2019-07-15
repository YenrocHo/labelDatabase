package com.fc.aden.util;

import java.util.UUID;

/**
* @Description ID生成器
*
* @author Created by zc on 2019/7/4
*/
public class IDGenerator {

    /**
     * 生成UUID
     *
     * @author Created by zc on 2019/7/4
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
