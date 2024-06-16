package com.szalai.funqtion.service;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.szalai.funqtion.util.OperationUtils;
import com.szalai.funqtion.exception.FunqtionException;

@Slf4j
@Service
public class FunqtionService {

    @Value("${funqtion.operation}")
    private String operation;

    public int initWithParams(int numberA, int numberB) {
        return runSafe(() -> handleOperation().apply(numberA, numberB));
    }

    private BiFunction<Integer, Integer, Integer> handleOperation() {
        switch (OperationUtils.fromString(operation)) {
            case SUM -> {
                return handleSum();
            }
            case SUB -> {
                return handleSub();
            }
            case MUL -> {
                return handleMul();
            }
            case DIV -> {
                return handleDiv();
            }
            default -> throw new FunqtionException("Invalid operation: " + operation);
        }
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

    private <T> T runSafe(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw FunqtionException.invalidOperationException(operation);
        }
    }
}
