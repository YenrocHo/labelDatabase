package com.fc.aden.common.base;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by LiYuan on 2019/3/26
 **/
public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    public static final String TOKRN_PREFIX = "token_";
    //LRU算法
    // 初始化本地缓存
    //生成本地缓存，初始化为1000，最大为10000，当超过10000时就会使用LRU算法（最小使用算法）进行清除，有效期是12小时
    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(24, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用该取值的时候，如果key没有对应的值就调用这个方法加载
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        loadingCache.put(key, value);
    }

    public static String getKey(String key) {
        String value = null;
        try {
            value = loadingCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("localCache get error", e);
        }
        return null;
    }
}
