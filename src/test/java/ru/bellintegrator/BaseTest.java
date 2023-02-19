package ru.bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver chromeDriver;

    @BeforeEach
    public void before(){
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     //   wait = new WebDriverWait(chromeDriver, 40);

    }
  @AfterEach
    public void closeBallTest(){
        chromeDriver.quit();
  }

}
