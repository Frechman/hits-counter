package ru.gavrilov.counter;

/**
 * @author gavrilov-sv
 * created on 29.06.2020
 */
public interface CounterService {

    Long getCurrentCounter();

    Long incrementCounterAndGet();

    Long resetCounter();
}
