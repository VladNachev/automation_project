package exersices;

import org.testng.Assert;
import org.testng.annotations.*;

public class ExersiceTest {
    @DataProvider(name = "testData")
    public Object[][] testData1() {
        return new Object[][]{
                {9, 5, 14},
                {12, 8, 4},
                {3, 6, 18},
                {15, 5, 3}
        };
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        System.out.println("Running a test!");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        System.out.println("End of test!");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        System.out.println("Setting up for test");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        System.out.println("Cleaning up after tests");
    }

    // A + B
    @Test(dataProvider = "testData")
    public void testExersiceTest1(int a, int b, int expectedResult) {
        System.out.println("Test is running........");
        int actualResult = a + b;
        Assert.assertEquals(actualResult, expectedResult);

    }

    //    A-B
    @Test(dataProvider = "testData")
    public void testExersiceTest2(int a, int b, int expectedResult) {
        int actualResult = a - b;
        System.out.println("Test is running........");
        Assert.assertEquals(actualResult, expectedResult);
    }

    //    A*B
    @Test(dataProvider = "testData")
    public void testExersiceTest3(int a, int b, int expectedtedResult) {
        int actualResult = a * b;
        System.out.println("Test is running........");
        Assert.assertEquals(actualResult, expectedtedResult);
    }

    //    A/B
    @Test(dataProvider = "testData")
    public void testExersiceTest4(int a, int b, int expectedResult) {
        int actualResult = a / b;
        System.out.println("Test is running........");
        Assert.assertEquals(actualResult, expectedResult);
    }


}
