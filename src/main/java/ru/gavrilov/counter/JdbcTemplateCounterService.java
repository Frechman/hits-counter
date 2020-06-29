package ru.gavrilov.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gavrilov-sv
 * created on 29.06.2020
 */
@Service
@Qualifier("jdbcTemplate")
public class JdbcTemplateCounterService implements CounterService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCounterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getCurrentCounter() {
        return jdbcTemplate.queryForObject("SELECT counter FROM hits", Long.class);
    }

    @Transactional
    @Override
    public Long incrementCounterAndGet() {
        jdbcTemplate.update("UPDATE hits SET counter = counter + 1");
        return getCurrentCounter();
    }

    @Override
    public Long resetCounter() {
        jdbcTemplate.update("UPDATE hits SET counter = 0");
        return 0L;
    }
}
