package com.siival.ratelimit.ratelimit;




public abstract class RateLimiter {

    public  void counterConsume(String key, long limit, long  refreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval){}

    public  void tokenConsume(String key, long limit, long  refreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval){}
}
