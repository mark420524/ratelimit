package com.siival.ratelimit.ratelimit.impl;

import com.siival.ratelimit.exception.BusinessErrorEnum;
import com.siival.ratelimit.exception.BusinessException;
import com.siival.ratelimit.ratelimit.RateLimiter;
import com.siival.ratelimit.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author syj
 * CreateTime    2018/09/05
 * Description
 */
@Slf4j
public class RedisRateLimiterCounterImpl extends RateLimiter {

    @Autowired
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    public RedisRateLimiterCounterImpl(DefaultRedisScript<Long> redisScript) {
        this.redisScript = redisScript;
    }

    @Override
    public void counterConsume(String key, long limit, long refreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval) {
        List<String> keyList = new ArrayList<>();
        String hashTag=getHashTag(key);
        keyList.add(hashTag);
        String result = redisTemplate.execute(redisScript, keyList, String.valueOf(limit), String.valueOf(refreshInterval)).toString();
        if (Const.REDIS_ERROR.equals(result)) {
            throw new BusinessException(BusinessErrorEnum.TOO_MANY_REQUESTS);
        }
    }

    private static String getHashTag(String key) {
        if (key.indexOf(Const.HASH_TAG_SUFFIX) > key.indexOf(Const.HASH_TAG_PRFIX)+ 1) {
            return key;
        }
        key = key.replaceAll(Const.HASH_TAG_PRFIX, "");
        key = key.replaceAll(Const.HASH_TAG_SUFFIX, "");
        return new StringBuffer("{").append(key).append("}").toString();
    }

}
