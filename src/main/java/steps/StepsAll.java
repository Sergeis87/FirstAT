package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class StepsAll {
    private static WebDriverWait wait;
    private static WebDriver chromeDriver;

    @Step("Переходим на сайт {url}")
    public static void openSite(String url, String title, WebDriver currentDriver){
        chromeDriver=currentDriver;
        chromeDriver.get(url);
        wait = new WebDriverWait(chromeDriver, 40);
        wait.until(ExpectedConditions.titleIs(title));
    }
}
