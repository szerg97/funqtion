package com.szalai.funqtion;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OperationUtils {

    public OperationType fromString(String operation) {
        return OperationType.valueOf(operation.toUpperCase());
    }
}
