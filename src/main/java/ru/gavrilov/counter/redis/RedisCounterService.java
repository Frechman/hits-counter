package ru.gavrilov.counter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import ru.gavrilov.counter.CounterService;

import javax.annotation.PostConstruct;

/**
 * @author gavrilov-sv
 * created on 29.06.2020
 */
@Service
@Qualifier("redis")
public class RedisCounterService implements CounterService {

    private static final String KEY_COUNTER = "redis-counter";
    private static final long INIT_COUNTER_VALUE = 0;

    private final RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    public RedisCounterService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
        valueOperations.setIfAbsent(KEY_COUNTER, INIT_COUNTER_VALUE);
    }

    @Override
    public Long getCurrentCounter() {
        return Long.valueOf(String.valueOf(valueOperations.get(KEY_COUNTER)));
    }

    @Override
    public Long incrementCounterAndGet() {
        return valueOperations.increment(KEY_COUNTER);
    }

    @Override
    public Long resetCounter() {
        valueOperations.set(KEY_COUNTER, INIT_COUNTER_VALUE);
        return INIT_COUNTER_VALUE;
    }
}
