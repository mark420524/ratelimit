package cn.org.zhixiang.util;

/**
 * Description :
 *
 * @author  syj
 * CreateTime    2018/09/05
 * Description   全局静态常量类
 */
public class Const {
    /**
     * 配置rateLimit的前缀
     */
    public static final String PREFIX="limit";
    /**
     * 集群模式指定slot的hash tag
     */
    public static final String HASH_TAG="{mmm}";
    /**
     * hash tag 前缀
     */
    public static final String HASH_TAG_PRFIX="{";
    /**
     * hash tag 后缀
     */
    public static final String HASH_TAG_SUFFIX="}";

    /**
     * 自定义拦截方式时的key
     */
    public static final String CUSTOM="rateLimitCustom";
    /**
     * redis返回错误信息
     */
    public static final String REDIS_ERROR="-1";





}
