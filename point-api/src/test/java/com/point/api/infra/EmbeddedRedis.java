package com.point.api.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class EmbeddedRedis {

    @Value("${spring.redis.port}")
    private int port;

    private RedisServer redisServer;

    public EmbeddedRedis() {
        this.redisServer = new RedisServer(port);
    }

    @PostConstruct
    public void postConstruct() {
        if (redisServer == null || !redisServer.isActive()) {
            redisServer = new RedisServer(port);
            redisServer.start();
        }
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
