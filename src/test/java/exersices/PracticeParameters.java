package exersices;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PracticeParameters {
    @Parameters({"num1", "num2", "sum"})
    @Test
    public void testParameters(int num1, int num2, int sum){
        System.out.println("Runing the test....");
        int actualResult = num1 + num2;
        Assert.assertEquals(actualResult, sum);
    }

}
