package com.tutorial.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Configuration
@EnableCaching//(proxyTargetClass = true)
// @ConditionalOnBean(name = "redisProperties")
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Resource
    RedisProperties redisProperties;

    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // set default serializer
        template.setExposeConnection(true);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.afterPropertiesSet();
        return template;
    }


    @Bean
    @Primary
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//        redisCacheManager.setTransactionAware(true);
//        redisCacheManager.setLoadRemoteCachesOnStartup(false);
//        redisCacheManager.setUsePrefix(true);
/*
        // 自定义配置固定键值的过期时间
        if (redisProperties.getExpires()!=null){
            log.info("自定义 cacheName 缓存时间如下：");
            redisProperties.getExpires().forEach((k, v)->log.info(k + " -> " + v + " 秒"));
            redisCacheManager.setExpires(redisProperties.getExpires());
        }

        if (redisProperties.getDefaultExpiration() != null) {
            redisCacheManager.setDefaultExpiration(redisProperties.getDefaultExpiration());
        }

///        redisCacheManager.setCachePrefix();

        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheManager.setUsePrefix(redisProperties.isUseKeyPrefix());
        }
*/

        return redisCacheManager;
    }

    //@Bean
    /*@Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();

///            $$EnhancerBySpringCGLIB$$
//            String className = target.getClass().getName();
//            if (className.indexOf(ENHANCER_BY_SPRING_CGLIB)>0){
//                className = className.substring(0, className.indexOf(ENHANCER_BY_SPRING_CGLIB));
//            }
            sb.append(method.getName());
            if (params.length > 0) {
                sb.append(":");
                Arrays.stream(params).forEach(param -> sb.append(Objects.nonNull(param) ? param.toString() : ""));
            }

            log.debug("redis key: " + sb.toString());
            return sb.toString();
        };
    }*/
    @Override
    //@Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

}

