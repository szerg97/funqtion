package com.szalai.funqtion.model;

import lombok.Getter;

@Getter
public enum OperationType {
    SUM("sum"),
    SUB("sub"),
    MUL("mul"),
    DIV("div");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }
}
