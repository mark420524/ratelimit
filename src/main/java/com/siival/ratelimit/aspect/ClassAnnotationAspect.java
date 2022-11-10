package com.siival.ratelimit.aspect;

import com.siival.ratelimit.algorithm.RateLimiterAlgorithm;
import com.siival.ratelimit.annotation.ClassRateLimit;
import com.siival.ratelimit.util.RateLimiterUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/05
 * Description   MethodRateLimit注解切面类
 */
@Slf4j
@Aspect
@Component
public class ClassAnnotationAspect {

    @Autowired
    private RateLimiterAlgorithm rateLimiterAlgorithm;


    /**
     *
     * @param classRateLimit 注解
     */
    @Pointcut("@within(classRateLimit)")
    public void annotationPointcut(ClassRateLimit classRateLimit) {
    }


    /**
     *
     * @param joinPoint 切点
     * @param classRateLimit 注解
     */
    @Before("@within(classRateLimit)")
    public void doBefore(JoinPoint joinPoint, ClassRateLimit classRateLimit) {
        String key= RateLimiterUtil.getRateKey(joinPoint,classRateLimit.checkType(),classRateLimit.name());
        rateLimiterAlgorithm.consume(key,classRateLimit.limit(),classRateLimit.refreshInterval(),classRateLimit.tokenBucketStepNum(),classRateLimit.tokenBucketTimeInterval());
    }



}
