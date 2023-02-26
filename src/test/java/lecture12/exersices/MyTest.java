package lecture12.exersices;

import org.openqa.selenium.chrome.ChromeDriver;

public class MyTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        driver.manage().window().maximize();

        driver.close();

    }



}

