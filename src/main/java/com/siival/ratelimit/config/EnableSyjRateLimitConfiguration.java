package com.siival.ratelimit.config;


import com.siival.ratelimit.ratelimit.RateLimiter;
import com.siival.ratelimit.ratelimit.impl.RedisRateLimiterCounterImpl;
import com.siival.ratelimit.ratelimit.impl.RedisRateLimiterTokenBucketImpl;
import com.siival.ratelimit.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;


/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/05
 * Description
 */
@Slf4j
@Configuration
@ComponentScan(basePackages="com.siival.ratelimit")
public class EnableSyjRateLimitConfiguration {

    /*
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

     */

    @Bean(name = "rateLimiter")
    @ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "token")
    public RateLimiter tokenRateLimiter() {
        DefaultRedisScript<Long> consumeRedisScript=new DefaultRedisScript();
        consumeRedisScript.setResultType(Long.class);
        consumeRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-tokenBucket.lua")));
        return new RedisRateLimiterTokenBucketImpl(consumeRedisScript);
    }

    @Bean(name = "rateLimiter")
    @ConditionalOnProperty(prefix = Const.PREFIX, name = "algorithm", havingValue = "counter", matchIfMissing = true)
    public RateLimiter counterRateLimiter() {
        DefaultRedisScript<Long> redisScript=new DefaultRedisScript();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-counter.lua")));
        return new RedisRateLimiterCounterImpl(redisScript);
    }






}
