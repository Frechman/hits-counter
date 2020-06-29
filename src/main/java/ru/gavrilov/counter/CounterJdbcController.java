package ru.gavrilov.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author gavrilov-sv
 * created on 29.06.2020
 */
@RestController
@RequestMapping("/")
public class CounterJdbcController {

    private final CounterService counterService;

    @Autowired
    public CounterJdbcController(@Qualifier("jdbcTemplate") CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/")
    public ResponseEntity<Long> getCounter() {
        final Long counter = counterService.getCurrentCounter();
        return ResponseEntity.ok(counter);
    }

    @PostMapping("/")
    public ResponseEntity<Long> increaseCounter() {
        final Long counter = counterService.incrementCounterAndGet();
        return ResponseEntity.ok(counter);
    }

    @DeleteMapping("/")
    public ResponseEntity<Long> resetCounter() {
        final Long counter = counterService.resetCounter();
        return ResponseEntity.ok(counter);
    }
}
