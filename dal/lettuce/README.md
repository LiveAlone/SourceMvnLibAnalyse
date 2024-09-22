# Spring-Data-Redis K-V数据抽象
类似JDBC, Data-Redis通过RedisTemplate 对Redis数据类型高度抽象。
也可以获取 Jedis或者Lettuce Client客户端, 原始类库操作。 

## RedisConnectionFactory 支持不同 Client 建连方式
1. Jedis 阻塞式官方客户端
2. Lettuce 响应式客户端。

### RedisConnectionFactory, RedisConnection

Spring定义Redis链接语义，支持不同客户端链接方式。 RedisConnection 本身语义非线程安全，多线程使用 RedisTemplate。
或者通过 GetNativeConnection 获取原生客户端，lettuce StatefulRedisConnection 线程安全。

Redis Master/Replica setup 支持读写分离。写入Master 读取Slaver。

### RedisTemplate

通过 Operations 对不同数据雷星操作，HyperLogLog、List、Set、ZSet、Hash、String等。

Template 中定义了Key-Value的序列化方式，自定义 Factory, Template定义属性。

```java
@Configuration
class MyConfig {

  @Bean
  LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
  }

  @Bean
  RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }
}
```

StringRedisTemplate 默认Key-Value字符串类型，写入Redis原始String未序列化结果。


### 序列化

RedisSerializer 默认使用JDK序列化(JdkSerializationRedisSerializer)，也可以自定义实现。

















