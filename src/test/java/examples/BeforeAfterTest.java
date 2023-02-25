package examples;

import org.testng.annotations.*;

public class BeforeAfterTest {
    @BeforeTest
    public void login() {
        System.out.println("Before all test methods");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test method1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method2");
    }

    @AfterTest
    public void logout() {
        System.out.println("After all the test methods");
    }
}