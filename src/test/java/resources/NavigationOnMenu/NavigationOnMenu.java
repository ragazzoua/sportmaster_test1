package resources.NavigationOnMenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NavigationOnMenu {

    public void navigationOnMenu(String item, String submenu){
        $$("div").find(text("Каталог товаров")).click();
        $$("span").filterBy(cssClass("newMenu__itemTitle\n")).findBy(text(item)).click();
        $$("a").filterBy(cssClass("newSubmenuList__link\n")).findBy(text(submenu)).click();
    }

    public void settingLeftFilters(String category, String... brands){
        for (String brand : brands) {
            $(By.xpath(String.format("//h3[@class='active'][contains(.,'%s')]", category))).scrollTo();
            $$("a").filterBy(cssClass("sm-category__facet-value")).find(text(brand)).click();
        }
    }

    public void settingSortingFilter(String option, String... price){
        $("div").scrollTo();
        $$("div.sm-category__main-sorting_pseudoselect_trigger").first().click();
        ElementsCollection collections = $$("a").filterBy(cssClass("ajax-facet-value")).filter(text(option));

        if(price.length > 0){
            collections.last().click();
        } else {
            collections.first().click();
        }
        $(By.xpath("//h3[@class='active'][contains(.,'Бренд')]")).scrollTo();
    }
}
