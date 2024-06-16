package com.szalai.funqtion.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "funqtion", name = {"auto"}, havingValue = "true")
public class ScheduledService {

    private final FunqtionService funqtionService;

    @Scheduled(fixedRate = 3000)
    public void scheduledRandom() {
        funqtionService.initWithParams((int) (Math.random() * 10), (int) (Math.random() * 10));
    }
}
