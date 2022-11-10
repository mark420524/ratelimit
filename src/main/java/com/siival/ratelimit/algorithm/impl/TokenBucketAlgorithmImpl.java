package com.siival.ratelimit.algorithm.impl;

import com.siival.ratelimit.algorithm.RateLimiterAlgorithm;
import com.siival.ratelimit.ratelimit.RateLimiter;
import com.siival.ratelimit.util.Const;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/05
 * Description
 */
@Service
@DependsOn("rateLimiter")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "token")
public class TokenBucketAlgorithmImpl implements RateLimiterAlgorithm {

    @NonNull
    private RateLimiter rateLimiter;



    public void consume(String key, long limit, long  refreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval) {
        rateLimiter.tokenConsume(key,limit, refreshInterval,tokenBucketStepNum,tokenBucketTimeInterval);
    }

}
