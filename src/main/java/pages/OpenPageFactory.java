package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

/**
 * В классе описана проверка сравнения цен покупки и продажи, доллара и евро
 * */
public class OpenPageFactory {
    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, xpath = "//*[@title = \"Поиск\"]")
    WebElement searchField;
    @FindBy(how = How.XPATH,xpath = "(//span[@class='main-page-exchange__rate'])[2]")
    WebElement bankSellUsd;
    @FindBy(how = How.XPATH,xpath = "(//span[@class='main-page-exchange__rate'])[1]")
    WebElement bankByUsd;
    @FindBy(how = How.XPATH,xpath = "(//span[@class='main-page-exchange__rate'])[4]")
    WebElement bankSellEur;
    @FindBy(how = How.XPATH,xpath = "(//span[@class='main-page-exchange__rate'])[3]")
    WebElement bankByEur;

    @FindBy(how = How.XPATH, xpath ="//h3[text()='Банк Открытие: кредит наличными — под 8,9% каждому']" )
    WebElement goButton;

    @FindBy(how = How.XPATH, using = "//h3[text()='Банк Открытие: кредит наличными — под 8,9% каждому']")
    List<WebElement> result;


    /**
     Метод вызывает chromeDriver
     */
    public OpenPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     Метод вызывает поиск
     @param word содержит слово "Открытие"
     */
    public void find(String word){
        searchField.click();
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
    }

    /**
     Метод вызывает нажатие на 'Банк Открытие: кредит наличными — под 8,9% каждому'
     */
    public void go(){
        goButton.click();
    }

    /**
     Метод возвращает результат
     @return результат из списка
     */
    public List<WebElement> getResult() {
        return result;
    }

    /**
     Метод возвращает цену
     @return цену продажи доллара
     */
    public WebElement getSellUsd(){
        return bankSellUsd;
    }

    /**
     Метод возвращает цену
     @return цену покупки доллара
     */
    public WebElement getByUsd(){
        return bankByUsd;
    }

    /**
     Метод возвращает цену
     @return цену продажии Евро
     */
    public WebElement getSellEur(){
        return bankSellEur;
    }

    /**
     Метод возвращает цену
     @return цену покупки Евро
     */
    public WebElement getByEur() {
        return bankByEur;
    }
}

