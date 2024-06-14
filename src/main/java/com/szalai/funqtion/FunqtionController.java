package com.szalai.funqtion;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FunqtionController {

    private final FunqtionService service;

    @GetMapping("/{numberA}/{numberB}")
    public ResponseEntity<Void> initiateSequence(@PathVariable int numberA, @PathVariable int numberB) {
        try {
            service.initWithParams(numberA, numberB);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
