package org.yqj.source.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {

//    @Bean(name = "springSessionDefaultRedisSerializer")
//    public RedisSerializer springSessionDefaultRedisSerializer() {
//        return RedisSerializer.json();
//    }

//    @Bean
//    public CookieHttpSessionIdResolver sessionIdResolver() {
//        // 创建 CookieHttpSessionIdResolver 对象
//        CookieHttpSessionIdResolver sessionIdResolver = new CookieHttpSessionIdResolver();
//
//        // 创建 DefaultCookieSerializer 对象
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        sessionIdResolver.setCookieSerializer(cookieSerializer); // 设置到 sessionIdResolver 中
//        cookieSerializer.setCookieName("JSESSIONID");
//
//        return sessionIdResolver;
//    }

//    @Bean
//    public HeaderHttpSessionIdResolver sessionIdResolver() {
////        return HeaderHttpSessionIdResolver.xAuthToken();
////        return HeaderHttpSessionIdResolver.authenticationInfo();
//        return new HeaderHttpSessionIdResolver("token");
//    }

}
