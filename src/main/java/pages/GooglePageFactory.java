package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.awt.*;
import java.util.List;
/**
 * В классе описана проверка поиска на странице Гугл по слову Гладиолус
 * */
public class GooglePageFactory {
    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, xpath = "//*[@title = \"Поиск\"]")
    WebElement searchField;
    @FindBy(how = How.XPATH,xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[5]/center/input[1]")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//cite[text()='https://ru.wikipedia.org']")
    List<WebElement> result;

    /**
     * В классе описана проверка поиска на странице Гугл по слову Гладиолус
     * */
    public GooglePageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     Метод вызывает поиск
     @param word содержит слово "Гладиолус"
     */
    public void find(String word){
        searchField.click();
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
    }

    /**
     Метод возвращает результат
     @return результат из списка
     */
    public List<WebElement> getResult() {
        return result;
    }
}
