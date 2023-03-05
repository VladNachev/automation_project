package lecture14.exersices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Test scenario - Successful registration
// 1. Navigate to login page
// 2. Click on Register
// 3. Validate the url has changed
// 4. Validate the Sign up header is shown
// 5. Enter username
// 6. Enter email
// 7. Enter password
// 8. Confirm password
// 9. Click sign in button
// 10. Validate that a pop up appears indicating a successful registration
// 11. Validate the user is properly redirected


public class NewUserTest {
    final String baseUrl = "http://training.skillo-bg.com:4200";
    final String registerUrl = baseUrl + "/users/register";
    final String homePage = baseUrl + "/posts/all";

    ChromeDriver driver;

    @BeforeMethod
    public void setupDrive(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://training.skillo-bg.com:4200/posts/all");

    }

    @Test
    public void registerUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("1. Navigate to login page");
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-login")));
        //WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        //loginButton.click();
        clickElement(By.id("nav-link-login"), 5);

        System.out.println("2. Click on Register");
        //WebElement registerLink = driver.findElement(By.xpath("//a[text()='Register']"));
        //WebElement registerLink = driver.findElement(By.xpath("//a[@href='/users/register']"));
        //WebElement registerLink = driver.findElement(By.cssSelector("a[href='/users/register']"));
        //WebElement registerLink = driver.findElement(By.linkText("Register"));
        //WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        //registerLink.click();
        clickElement(By.linkText("Register"), 5);

        System.out.println("3. Validate the url has changed");
        //String expectedUrl = "http://training.skillo-bg.com:4200/users/register";
        wait.until(ExpectedConditions.urlToBe(registerUrl));

        System.out.println("4. Validate the Sign up header is shown");
        WebElement signUpHeader = driver.findElement(By.xpath("//h4[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(signUpHeader));

        System.out.println("5. Enter username");
        WebElement usernameField = driver.findElement(By.name("username"));
//        usernameField.sendKeys("testuser00123");
//        Boolean isValid = usernameField.getAttribute("class").contains("is-valid");
//        Assert.assertTrue(isValid, "The field is invalid");
        // !!!!!!! Check the above boolen check... it's very important :) !!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        enterTextField(usernameField, "testuser2312142321");

        System.out.println("6. Enter email");
        WebElement emailField = driver.findElement(By.cssSelector("input[formcontrolname='email']"));
        enterTextField(emailField, "testrewr213@abv.bg");

        System.out.println("7. Enter password");
        WebElement enterPasswordField = driver.findElement(By.id("defaultRegisterFormPassword"));
        enterTextField(enterPasswordField, "88aaaaaa44");

        System.out.println("8. Confirm password");
        WebElement confirmPasswordField = driver.findElement(By.id("defaultRegisterPhonePassword"));
        enterTextField(confirmPasswordField, "88aaaaaa44");

        System.out.println("9. Click sign in button");
        clickElement(By.id("sign-in-button"), 3);

        System.out.println("10. Validate that a pop up appears indicating a successful registration");

        // $$('div')
        // $$("div[class='toast-message ng-star-inserted']")
        // $$('div.toast-message')
        // $$('.toast-message')

        WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        String ToastMsgText = toastMsg.getText();
        // Assert.assertEquals(ToastMsgText, "Successful register!", "Registration failed!");
        Assert.assertTrue(ToastMsgText.contains("Successful register!"), "Required text not displayed");

        System.out.println("11. Validate the user is properly redirected");
        wait.until(ExpectedConditions.urlToBe(homePage));

    }

    private WebElement clickElement(By locator, int timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;

    }

    private WebElement clickElementFluentWait(By locator, int totalTimeout, int retryTimeoutMs) {
        FluentWait<ChromeDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(retryTimeoutMs))
                .ignoring(TimeoutException.class);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;

    }

    private void enterTextField(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
        Boolean isValid = element.getAttribute("class").contains("is-valid");
        Assert.assertTrue(isValid, "The field is invalid");



}


    @AfterMethod
    public void teardown(){
        driver.close();

    }
}
