package ru.bellintegrator;

import io.qameta.allure.Feature;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.GooglePageFactory;
import pages.OpenPageFactory;
import pages.YaMarketPage;

import java.util.List;

public class Tests extends BaseTest {
    @Disabled
    @Feature("Проверка результатов поиска Гладиолус")
    @DisplayName("Проверка результата поиска с помощью PF")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Гладиолус, https://ru.wikipedia.org"})
    public void testPFGoogle(String word) {
        chromeDriver.get("https://www.google.com/");
        GooglePageFactory googlePageFactory = PageFactory.initElements(chromeDriver, GooglePageFactory.class);
        googlePageFactory.find(word);
        Assertions.assertTrue(googlePageFactory.getResult().stream().anyMatch(x -> x.getText()
                .contains("https://ru.wikipedia.org")), "Википедия не найдена, по слову " + word);
    }

    @Disabled
    @Feature("Проверка результата поиска Открытие")
    @DisplayName("Проверка результата поиска с помощью PF")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Открытие, Банк Открытие: кредит наличными — под 8,9% каждому"})
    public void testPFOpen(String word) {
        chromeDriver.get("https://www.google.com/");
        OpenPageFactory openPageFactory = PageFactory.initElements(chromeDriver, OpenPageFactory.class);
        openPageFactory.find(word);
        Assertions.assertTrue(openPageFactory.getResult().stream().anyMatch(x -> x.getText()
                .contains("Банк Открытие: кредит наличными — под 8,9% каждому")), "Запись не найдена" + word);
        openPageFactory.go();

        System.out.println(openPageFactory.getByUsd().getText());
        System.out.println(openPageFactory.getSellUsd().getText());
        System.out.println(openPageFactory.getByEur().getText());
        System.out.println(openPageFactory.getSellEur().getText());
        Assertions.assertTrue(Float.parseFloat(openPageFactory.getByUsd().getText().replace(",", ".")) < Float.parseFloat(openPageFactory.getSellUsd().getText().replace(",", ".")));
        Assertions.assertTrue(Float.parseFloat(openPageFactory.getByEur().getText().replace(",", ".")) < Float.parseFloat(openPageFactory.getSellEur().getText().replace(",", ".")));
    }
//@Disabled
//    @Feature("Проверка результата поиска Таблица")
//    @DisplayName("Проверка результата поиска с помощью PF")
//    @ParameterizedTest(name = "{displayName}: {arguments}")
//     @CsvSource({"таблица, Таблица - Википедия"})
//    public void testPFYa(String word)  {
//        chromeDriver.get("https://www.google.com/");
//        YaPageFactory yaPageFactory = PageFactory.initElements(chromeDriver, YaPageFactory.class);
//        yaPageFactory.find(word);
//        Assertions.assertTrue(yaPageFactory.getResult().stream().anyMatch(x->x.getText()
//                .contains("Таблица - Википедия")), "Not found " + word);
//        yaPageFactory.go();
//
//        ArrayList<Prepod> listPrepod = yaPageFactory.getPrepods();
//
//        Assertions.assertEquals(listPrepod.get(0).getName(), "Сергей");
//        Assertions.assertEquals(listPrepod.get(0).getPatronimyc(), "Владимирович");
//        Assertions.assertEquals(listPrepod.get(listPrepod.size()-1).getName(), "Сергей");
//        Assertions.assertEquals(listPrepod.get(listPrepod.size()-1).getPatronimyc(), "Адамович");
//    }

    @Feature("Проверка поиска товаров в яндексмаркет")
    @DisplayName("Проверка результата поиска с помощью PF")
    @Test
    public void testYaMarket()  {
        chromeDriver.get("https://ya.ru/");
        //       chromeDriver.get("https://market.yandex.ru/");
        YaMarketPage yaMarketPage = PageFactory.initElements(chromeDriver, YaMarketPage.class);

        yaMarketPage.allServices();
        yaMarketPage.marketButton();
        yaMarketPage.swich();
        yaMarketPage.catalogueButton();
        chromeDriver.navigate().refresh();
        yaMarketPage.catalogueButton();
        yaMarketPage.notesAndComps();
        yaMarketPage.notebooks();
//        yaMarketPage.setPriceFilter(10000, 900000);
//        yaMarketPage.selectManufacturer("Lenovo");
//        yaMarketPage.selectManufacturer("HUAWEI");
       // yaMarketPage.lenovocheckBox();
        //       chromeDriver.get("https://market.yandex.ru/catalog--noutbuki/54544/list?hid=91013&allowCollapsing=1&local-offers-first=0&glfilter=7893318%3A152981%2C459710&pricefrom=10000&priceto=900000");

//        Assert.assertTrue(yaMarketPage.productSnippetSize() > 12);
//        yaMarketPage.checkProductsTitle(List.of("Lenovo", "HUAWEI"));
//
//        yaMarketPage.goToFirstPage();

        String product = yaMarketPage.getFirstProduct().getText();
        yaMarketPage.search(product);
        String productAfterSearch = yaMarketPage.getFirstProduct().getText();
        yaMarketPage.checkTextContains(List.of(product), productAfterSearch);
    }
}
