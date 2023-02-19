package pages;

import DTO.Prepod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

/**
* В классе описана проверка поиска в таблице на странице Википедия
* */
public class YaPageFactory {
    private WebDriver chromeDriver;
    @FindBy(how = How.XPATH, xpath = "//*[@title = \"Поиск\"]")
    WebElement searchField;

    @FindBy(how = How.XPATH, xpath ="//h3[text()='Таблица - Википедия']" )
    WebElement goButton;

    @FindBy(how = How.XPATH, using = "//h3[text()='Таблица - Википедия']")
    List<WebElement> result;

    @FindBy(how = How.XPATH, using = "//th[text()='Фамилия']//ancestor::tr//following-sibling::tr[position()>0]")
    List<WebElement> prepods;

    /**
     Метод вызывает chromeDriver
     */
    public YaPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     Метод вызывает поиск
     @param word содержит слово "Таблица"
     */
    public void find(String word){
        searchField.click();
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
    }

    /**
     Метод вызывает нажатие на 'Таблица - Википедия'
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
     * Массив таблицы преподавателей
     * */
    public ArrayList<Prepod> getPrepods() {

        ArrayList<Prepod> allPrepods = new ArrayList<Prepod>();
        for (WebElement prepodWebElement : prepods)
        {
            String FIO = prepodWebElement.getText();
            String[] FIOArray = FIO.split(" ");
            allPrepods.add(new Prepod(FIOArray[0], FIOArray[1], FIOArray[2]));
        }
        return allPrepods;

    }
}
