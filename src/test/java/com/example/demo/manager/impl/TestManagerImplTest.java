package com.example.demo.manager.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestManagerImplTest {

    private TestManagerImpl testManagerImplUnderTest;

    @BeforeEach
    void setUp() {
        testManagerImplUnderTest = new TestManagerImpl();
    }

    @Test
    void testTestAsync() {
        assertThat(testManagerImplUnderTest.testAsync()).isEqualTo("testAsync");
    }

    @Test
    void testTestSync() {
        assertThat(testManagerImplUnderTest.testSync()).isEqualTo("testAsync");
    }

    @Test
    void testSayHello() {
        assertThat(testManagerImplUnderTest.sayHello()).isEqualTo("hello");
    }
}
