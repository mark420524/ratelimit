package com.siival.ratelimit.annotation;

import java.lang.annotation.*;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/04
 * Description   限流注解(应用于方法)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodRateLimit {
    /**
     *
     * @return CheckTypeEnum 限流类型。默认值：ALL。可选值：ALL,IP,USER,CUSTOM
     */
    CheckTypeEnum checkType() default CheckTypeEnum.ALL;
    /**
     *
     * @return 限流次数。默认值10
     */
    long limit() default 10;

    /**
     * method name
     * @return
     */
    String name() default "";
    /**
     *
     * @return 限流时间间隔,以秒为单位。默认值60
     */
    long refreshInterval() default 60;

    /**--------------------限流算法为令牌桶时的有效配置-----------------------**/
    /**
     *
     * @return 向令牌桶中添加数据的时间间隔,以秒为单位。默认值10秒
     */
    long tokenBucketTimeInterval() default 10;
    /**
     *
     * @return 每次为令牌桶中添加的令牌数量。默认值5个
     */
    long tokenBucketStepNum() default 5;

}
