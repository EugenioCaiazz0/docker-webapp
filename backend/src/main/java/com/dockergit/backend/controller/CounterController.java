package com.dockergit.backend.controller;

import java.util.Map;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    private final StringRedisTemplate redisTemplate;

    public CounterController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/api/counter")
    public Map<String, Object> counter() {

        Long counter = redisTemplate
                .opsForValue()
                .increment("counter");

        return Map.of(
                "message", "bro apriti ziopera",
                "service", "Redis OK",
                "counter", counter
        );
    }
}