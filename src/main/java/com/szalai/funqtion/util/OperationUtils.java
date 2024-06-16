package com.szalai.funqtion.util;

import com.szalai.funqtion.model.OperationType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class OperationUtils {

    public OperationType fromString(String operation) {
        return OperationType.valueOf(operation.toUpperCase());
    }
}
