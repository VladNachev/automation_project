package lecture13.exersices;

import com.github.dockerjava.api.model.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

// Test - successful login
// 0. Navigate to Home page
// 1. Navigate to Login page by clicking on the Login tab button
// 2. Validate the URL is correct
// 3. Validate the Sign in text is visible
// 4. Enter correct username
// 5. Enter correcr password
// 6. Click on the Sign in button
// 7. Validate the URL is correct
// 8. Validate that there is a Profile tab button visibile
// 9. Validate that there is a New Post tab buttion visible
// 10. Click on the Profile button
// 11. Validate the correct username is displayed

public class LoginTest {
    ChromeDriver driver;
    final String homepageURL = "http://training.skillo-bg.com:4200/posts/all";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://training.skillo-bg.com:4200/posts/all");

    }

    @Test(invocationCount = 3)
    public void testLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // The implicitly wait above above affects only findElements
        System.out.println("1. Navigate to Login page by clicking on the Login tab button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("2. Validate the URL is correct");
        String expectedUrl = "http://training.skillo-bg.com:4200/users/login";

        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // String currentUrl = driver.getCurrentUrl();
        // Assert.assertEquals(currentUrl, expectedUrl, "Invalid URL!!!");

        System.out.println("3. Validate the Sign in text is visible");
        String expectedText = "Sign in";
        WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInElement.isDisplayed(), "Sign in form is not visible");

        System.out.println("4. Enter correct username");
        WebElement usernameField = driver.findElement(By.name("usernameOrEmail"));
        usernameField.sendKeys("***********");

        System.out.println("5. Enter correcr password");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("******************");

        System.out.println("6. Click on the Sign in button");
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        signInButton.click();

        System.out.println("7. Validate the URL is correct");
        wait.until(ExpectedConditions.urlToBe(homepageURL));
        // String currentUrl = driver.getCurrentUrl();
        // Assert.assertEquals(currentUrl, expectedUrl, "Invalid URL!!!");

        System.out.println("8. Validate that there is a Profile tab button visibile");
        WebElement profileLink = driver.findElement(By.xpath("//a[text()='Profile']"));
        Assert.assertTrue(profileLink.isDisplayed(), "Profile link not vissible!");

        System.out.println("9. Validate that there is a New Post tab buttion visible");
        WebElement newPostLink = driver.findElement(By.linkText("New post"));
        Assert.assertTrue(newPostLink.isDisplayed(), "New Post link not vissible!");

        System.out.println("10. Click on the Profile button");
        profileLink.click();

        System.out.println("11. Validate the correct username is displayed");
        WebElement nameDisplayed = driver.findElement(By.xpath("//h2[text()='VladTest']"));
        Assert.assertEquals(nameDisplayed.getText(), "VladTest", "Invalid username displayed");










    }

    @AfterMethod
    public void cleanup(){
        driver.close();
    }
}
