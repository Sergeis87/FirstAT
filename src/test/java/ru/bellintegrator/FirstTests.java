package ru.bellintegrator;

import org.junit.jupiter.api.*;

public class FirstTests {
    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }
    @Test
    public void firstTest(){
        System.out.println("firstTest");
        Assertions.assertTrue(1==1, "One not equals one");
    }

    @Test
    public void secondTest(){
        System.out.println("second test");
        Assertions.assertTrue(1==2, "One not equals two");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("AfterAll");
    }

}
