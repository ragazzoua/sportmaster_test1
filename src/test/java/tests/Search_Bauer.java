package tests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.Driver.BrowserDriver;
import resources.NavigationOnMenu.NavigationOnMenu;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Search_Bauer {
    NavigationOnMenu navigation = null;
    BrowserDriver browserDriver = null;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        navigation = new NavigationOnMenu();
        browserDriver = new BrowserDriver();
        browserDriver.settingBrowserDriver();
    }

    @AfterMethod
    void afterTest(){
        open("/basket/checkout.do");
        $(By.xpath("//span[@data-selenium='remove_from_basket'][contains(.,'Удалить')]")).click();
        checkBasket(null, null, false);
    }

    @Test
    public void searchSkatesOnMenuOnCategory() {
        open("");

        navigation.navigationOnMenu("Ледовые коньки и хоккей", "КОНЬКИ");

        navigation.settingLeftFilters("Бренд","Bauer");

        navigation.settingSortingFilter("По цене", "descent");

        buyObject();

        checkBasket("1", "Коньки хоккейные Bauer BTH18 SUPREME 2S", true);
    }

    @Test
    public void searchSkatesOnMenuOnKindOfSport(){
        open("");

        navigation.navigationOnMenu("Командные виды спорта", "ХОККЕЙ");

        $(By.xpath("//td[contains(@class, 'first-child')]//h2[contains(.,'Коньки')]")).click();

        navigation.settingLeftFilters("Бренд","Bauer");

        navigation.settingSortingFilter("По цене", "descent");

        buyObject();

        checkBasket("1", "Коньки хоккейные Bauer BTH18 SUPREME 2S", true);
    }

    @Test
    public void searchScatesOnProductSearch(){
        open("");

        $(By.xpath("//input[@data-selenium='product_search_input']")).setValue("Bauer BTH18 SUPREME 2S").pressEnter();

        $(By.xpath("//a[@title='Коньки хоккейные Bauer BTH18 SUPREME 2S']")).click();

        buyObject();

        checkBasket("1", "Коньки хоккейные Bauer BTH18 SUPREME 2S", true);
    }

    private void buyObject(){
        $("div").scrollTo();

        $(By.xpath("//a[@title='Коньки хоккейные Bauer BTH18 SUPREME 2S']")).click();

        $(By.xpath("//a[@data-selenium='add_to_basket']")).click();

        $(By.xpath("//h1[contains(.,'Выберите размер')]")).should(exist);
        getWebDriver().findElements(By.xpath("//div[@class=\"sm-goods__param-value\"]")).get(0).click();

        $(By.xpath("//a[contains(@data-selenium,'go_to_basket')]")).click();
    }

    private void checkBasket(String quantity, String name_product, boolean expectation){
       SelenideElement element_quantity = $(By.xpath(String.format("//h1[@class='sm-basket_title'][contains(.,'Корзина (%s товар)')]", quantity)));
       SelenideElement element_name_product = $(By.xpath(String.format("//span[@data-bind='text: name'][contains(.,'%s')]", name_product)));

       Assert.assertEquals(element_quantity.exists(), expectation, "the quantity of goods is correct");
       Assert.assertEquals(element_name_product.exists(), expectation, "product name is correct");
    }
}
