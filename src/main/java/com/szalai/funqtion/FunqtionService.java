package com.szalai.funqtion;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Slf4j
@Service
public class FunqtionService {

    @Value("${funqtion.operation}")
    private String operation;

    public void initWithParams(int numberA, int numberB) {
        runSafe(() -> {
            switch (OperationUtils.fromString(operation)) {
                case SUM -> logIt(handleSum()).accept(numberA, numberB);
                case SUB -> logIt(handleSub()).accept(numberA, numberB);
                case MUL -> logIt(handleMul()).accept(numberA, numberB);
                case DIV -> logIt(handleDiv()).accept(numberA, numberB);
            }
        });
    }

    private BiConsumer<Integer, Integer> logIt(BiFunction<Integer, Integer, Integer> handler) {
        return (numberA, numberB) -> {
            final int result = handler.apply(numberA, numberB);
            log.info(String.format("%s %s %s is: %s", numberA, operation, numberB, result));
        };
    }

    private BiFunction<Integer, Integer, Integer> handleSum() {
        return Integer::sum;
    }

    private BiFunction<Integer, Integer, Integer> handleSub() {
        return (numberA, numberB) -> numberA - numberB;
    }

    private BiFunction<Integer, Integer, Integer> handleMul() {
        return (numberA, numberB) -> numberA * numberB;
    }

    private BiFunction<Integer, Integer, Integer> handleDiv() {
        return (numberA, numberB) -> numberA / numberB;
    }

    private void runSafe(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw FunqtionException.invalidOperationException(operation);
        }
    }
}
