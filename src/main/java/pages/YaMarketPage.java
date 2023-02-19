package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

/**
 * В классе описана проверка поиска на странице Яндексмаркета
 * */
public class YaMarketPage  {
    private WebDriver chromeDriver;
    private WebDriverWait wait;
    private WebDriverWait longWait;

    By productSnippetLocator = By.xpath("//article[@data-autotest-id='product-snippet']//h3/a");
    By firstProductSnippetLocator = By.xpath("(//article[@data-autotest-id='product-snippet']//h3/a)[1]");

    @FindBy( how = How.XPATH, xpath = "//a[@title = 'Все сервисы']")
    WebElement allServices;
    @FindBy(how = How.XPATH, xpath = "//div[text() = 'Маркет']")
    WebElement marketButton;
    @FindBy(how = How.XPATH, xpath = "//*[text()='Каталог']")
    WebElement catalogueButton;
    @FindBy(how = How.XPATH, xpath = "//*[text()='Ноутбуки и компьютеры']")
    WebElement notesAndComps;
    @FindBy(how = How.XPATH, xpath = "//*[text()='Ноутбуки']")
    WebElement notesBooks;
    @FindBy(how = How.XPATH, xpath = "//label//span[text()='Lenovo']")
    WebElement lenovo;
    @FindBy(how = How.XPATH, xpath = "//span[text()='Показать всё']")
    WebElement showAll;
    //span[text()='Показать всё']
    //*[text()='Найти производителя']
    @FindBy(how = How.XPATH, xpath = "//label[text()='Найти производителя']/../input")
    WebElement findAll;
    //input[@type='text'])[2]
    @FindBy(how = How.XPATH, xpath = "//span[text()='HUAWEI']")
    WebElement huawei;
    @FindBy(how = How.XPATH, xpath = "//label[text()='Цена, ₽ от']/..//input")
    WebElement moneyMin;
    @FindBy(how = How.XPATH, xpath = "//label[text()='Цена, ₽ до']/..//input")
    WebElement moneyMax;

    @FindBy(how = How.XPATH, xpath = "//a[text()='Пользовательское соглашение']")
    WebElement solution;

    @FindBy(how = How.XPATH, xpath = "//span[text()='Вперёд']")
    WebElement nextPage;

    @FindBy(how = How.XPATH, xpath = "//span[@aria-label='Загрузка...']")
    WebElement loader;

    @FindBy(how = How.XPATH, xpath = "//div[@data-zone-name='SearchPager']")
    WebElement searchPager;
    @FindBy(how = How.XPATH, xpath = "//input[@id='header-search']")
    WebElement searchField;

    /**
     Метод вызывает chromeDriver
     */
    public YaMarketPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 10);
        this.longWait = new WebDriverWait(chromeDriver, 120);
    }

    /**
     Метод нажимает слева внизу на значёк все сервисы
     */
    public void allServices() {
        allServices.click();
    }
    /**
      Кликнуть по Маркет
     */
    public void marketButton(){
        marketButton.click();
    }
    /**
     Нажать на кнопку каталог
     */
    public void catalogueButton(){
        Actions actions =new Actions(chromeDriver);
           actions.moveToElement(catalogueButton).click().build().perform();

    }
    public void swich(){
        Set<String> tabs = chromeDriver.getWindowHandles();
        for(String tab:tabs)
            chromeDriver.switchTo().window(tab);
    }

        public void notesAndComps(){
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(notesAndComps).build().perform();
    }
    public void notebooks(){
        notesBooks.click();
    }

    public void setPriceFilter(int min, int max)  {
        moneyMin.click();
        moneyMin.sendKeys(String.valueOf(min));
        moneyMax.click();
        moneyMax.sendKeys(String.valueOf(max));

//        lenovo.click();
//        showAll.click();
//        wait.until(ExpectedConditions.visibilityOf(findAll)).sendKeys("hua");
//        wait.until(ExpectedConditions.elementToBeClickable(huawei)).click();
    }

    public void selectManufacturer(String name) {
        chromeDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        boolean findAllExist = !chromeDriver.findElements(By.xpath("//span[text()='Показать всё']")).isEmpty();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        if (findAllExist) { showAll.click(); }
        findAll.clear();
        findAll.sendKeys(name);
        longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label//span[text()='" + name + "']")));
        chromeDriver.findElement(By.xpath("//label//span[text()='" + name + "']")).click();
    }

    public int productSnippetSize() {
        goToSolution();
        List<WebElement> productSnippet = chromeDriver.findElements(productSnippetLocator);
        return productSnippet.size();
    }

    public void goToSolution() {
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(solution).perform();
    }

    public void checkTextContains(List<String> title, String text) {
        List<String> lowerCaseTitle = title.stream().map(a -> a.toLowerCase()).collect(Collectors.toList());

        System.out.println("Текст: " + text);
        String actualLowerCaseTitle = text.toLowerCase();

        boolean found = false;
        for (String t : lowerCaseTitle) {
            if (actualLowerCaseTitle.contains(t)) {
                found = true;
                break;
            }
        }

        if (!found) {
            Assert.fail("Элемент " + text + " не содержит ни одного из искомых значений: " + String.join(", ", title));
        }
    }

    public void checkProductsTitle(List<String> title) {
        do {
            goToSolution();
            List<WebElement> productSnippet = chromeDriver.findElements(productSnippetLocator);

            System.out.println("Количество товаров: " + productSnippet.size());
            for (WebElement as : productSnippet) {
                checkTextContains(title, as.getText());
            }

            if (!chromeDriver.findElements(By.xpath("//span[text()='Вперёд']")).isEmpty()) {
                Actions actions = new Actions(chromeDriver);
                actions.moveToElement(nextPage).click(nextPage).perform();

                wait.until(ExpectedConditions.visibilityOf(loader));
                actions.moveToElement(searchPager).perform();
                longWait.until(ExpectedConditions.invisibilityOf(loader));
            } else {
                break;
            }
        } while (true);
    }

    public void goToFirstPage() {
        String url = chromeDriver.getCurrentUrl();
        System.out.println(url);
        String newUrl = url.replaceAll("\\&page=\\d+", "");
        System.out.println(newUrl);
        chromeDriver.navigate().to(newUrl);
    }
    public WebElement getFirstProduct() {
        return chromeDriver.findElement(firstProductSnippetLocator);
    }
    public void search(String query){
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);
        longWait.until(ExpectedConditions.visibilityOfElementLocated(firstProductSnippetLocator));
    }
}
