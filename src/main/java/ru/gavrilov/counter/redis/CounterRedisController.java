package ru.gavrilov.counter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gavrilov.counter.CounterService;

/**
 * @author gavrilov-sv
 * created on 29.06.2020
 */
@RestController
@RequestMapping("/redis/")
public class CounterRedisController {

    private final CounterService counterService;

    @Autowired
    public CounterRedisController(RedisCounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/")
    public ResponseEntity<Long> getCounter() {
        return ResponseEntity.ok(counterService.getCurrentCounter());
    }

    @PostMapping("/")
    public ResponseEntity<Long> increaseCounter() {
        return ResponseEntity.ok(counterService.incrementCounterAndGet());
    }

    @DeleteMapping("/")
    public ResponseEntity<Long> resetCounter() {
        return ResponseEntity.ok(counterService.resetCounter());
    }
}
