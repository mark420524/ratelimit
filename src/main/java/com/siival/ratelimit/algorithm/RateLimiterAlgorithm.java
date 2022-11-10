package com.siival.ratelimit.algorithm;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/05
 * Description   算法策略接口
 */
public interface RateLimiterAlgorithm {

    void consume(String key, long limit, long refreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval);
}
