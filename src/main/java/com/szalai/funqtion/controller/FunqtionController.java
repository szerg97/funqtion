package com.szalai.funqtion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.szalai.funqtion.service.FunqtionService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FunqtionController {

    @Value("${funqtion.operation}")
    private String operation;
    private final FunqtionService service;

    @GetMapping("/{numberA}/{numberB}")
    public ResponseEntity<Void> initiateSequence(@PathVariable int numberA, @PathVariable int numberB) {
        try {
            log.info(String.format("%s %s %s is: %s", numberA, operation, numberB, service.initWithParams(numberA, numberB)));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
