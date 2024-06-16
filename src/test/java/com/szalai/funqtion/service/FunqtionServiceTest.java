package com.szalai.funqtion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.util.ReflectionTestUtils;

import com.szalai.funqtion.exception.FunqtionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FunqtionServiceTest {

    private FunqtionService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FunqtionService();
    }

    @Test
    void testSum() {
        ReflectionTestUtils.setField(underTest, "operation", "sum");
        assertEquals(8, underTest.initWithParams(3, 5));
    }

    @Test
    void testSub() {
        ReflectionTestUtils.setField(underTest, "operation", "sub");
        assertEquals(-2, underTest.initWithParams(3, 5));
    }

    @Test
    void testMul() {
        ReflectionTestUtils.setField(underTest, "operation", "mul");
        assertEquals(15, underTest.initWithParams(3, 5));
    }

    @Test
    void testDiv() {
        ReflectionTestUtils.setField(underTest, "operation", "div");
        assertEquals(0, underTest.initWithParams(3, 5));
    }

    @Test
    void testInvalidOperation() {
        ReflectionTestUtils.setField(underTest, "operation", "invalid");
        assertThatThrownBy(() -> underTest.initWithParams(3, 5))
                .hasMessage("Invalid operation: invalid")
                .isInstanceOfAny(FunqtionException.class);
    }
}