package testclass.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;

/**
 * Created by Alexandr.Vershok on 4/2/2017.
 */
public class ResultsPage extends BaseForm {

    private Label sortOrder = new Label(By.className("schema-order__link"), "Sort by...");
    private Label price = new Label(By.xpath("//div[@class='schema-order__popover']//span[contains(text(),'Дорогие')]"), " Expecsinve");
    private Label yearIn = new Label(By.xpath("//span[@class='schema-filter__checkbox-text'][contains(text(),'2017')]/preceding-sibling::span/span[@class='i-checkbox__faux']"), "Produced in ");
    private TextBox yearBefore = new TextBox(By.xpath("//input[@class='schema-filter-control__item schema-filter__number-input'][@placeholder='2017']"), "Produced before ");
    private Label mountain = new Label(By.xpath("//li//span[@class='schema-filter__checkbox-text'][contains(text(),'горный')]/preceding-sibling::span/span[@class='i-checkbox__faux']"), "Mountain bikes");
    private Label city = new Label(By.xpath("//li//span[@class='schema-filter__checkbox-text'][contains(text(),'городской')]/preceding-sibling::span/span[@class='i-checkbox__faux']"), "City bikes");
    private Label twentySixIncher = new Label(By.xpath("//li//span[@class='schema-filter__checkbox-text'][contains(text(),'26\"')]/preceding-sibling::span/span[@class='i-checkbox__faux']"), "26 inches wheel");
    private Label twentyNineIncher = new Label(By.xpath("//li//span[@class='schema-filter__checkbox-text'][contains(text(),'29\"')]/preceding-sibling::span/span[@class='i-checkbox__faux']"), "29 inches wheel");
    private Label favouriteItem;
    private Label favoritesList;
    private Label favsInCatalog;

    private Label bestBike;
    private String bikeName;
    private String year;
    private String madeFor;
    private String wheelRadius;

    public ResultsPage() {
        super(By.xpath("//h1[@class='schema-header__title']"), "Page with search results");
    }

    public void scrollTo(WebElement x) {
        ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].scrollIntoView(true);", x);
    }

    public void setUpFilter(String year, String madeFor, String wheelRadius) {
        this.year = year;
        this.madeFor = madeFor;
        this.wheelRadius = wheelRadius;

        switch (year) {
            case "in 2017":
                scrollTo(yearIn.getElement());
                yearIn.click();
                break;

            case "before 2017":
                scrollTo(yearBefore.getElement());
                yearBefore.setText("2016");
                break;
        }
        browser.waitForPageToLoad();
        switch (madeFor) {
            case "mountain":
                scrollTo(mountain.getElement());

                mountain.click();
                break;

            case "city":
                scrollTo(city.getElement());
                city.click();
        }

        browser.waitForPageToLoad();
        switch (wheelRadius) {
            case "26":
                scrollTo(twentySixIncher.getElement());
                twentySixIncher.click();
                break;


            case "29":
                scrollTo(twentySixIncher.getElement());
                twentyNineIncher.click();
                break;
        }


        sortOrder.click();
        price.click();
        info("Filters set");
        browser.waitForPageToLoad();
    }

    public void addToFavs() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bestBike = new Label(By.xpath("//div[@id='schema-products']/div[1]//div[@class='schema-product__title']/a"), "Best result");
        scrollTo(bestBike.getElement());
        bikeName = bestBike.getText();
        bestBike.click();
        favouriteItem = new Label(By.xpath("//span[@class='catalog-masthead-controls__input i-checkbox i-checkbox_star']/span"), "Add to favorites");
        favouriteItem.click();
        info("Item added");
    }

    public void openFavs() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        favoritesList = new Label(By.xpath("//a[contains(text(),'Закладки')]"), "Open Favorites list");
        favoritesList.click();
        favsInCatalog = new Label(By.xpath("//a[contains(text(),'Каталог')]"), "Check favorites of Catalog items");
        favsInCatalog.click();
    }

    public void findLatestAdded() {
        openFavs();
        List<WebElement> webElements;
        webElements = browser.getDriver().findElements(By.xpath("//strong[@class='pname']/a"));
        for (WebElement w : webElements) {
            if (w.getText().contains(bikeName)) {
                info("Bike found. Expected: " + w.getText()+ ". Actual: " + bikeName);
            } else {
                warn("Bike not found. Expected: " + w.getText()+ ". Actual: " + bikeName);
            }
        }
    }

    public void checkDetails() {
        Label favYear = new Label(By.xpath("//td[contains(.,'Дата выхода на рынок')]/following-sibling::td/span"));
        doAssert(year.split(" ")[1].equals(favYear.getText().split(" ")[0]), "Year is correct", "Year is wrong");
        if (madeFor.equals("city")) {
            Label favFor = new Label(By.xpath("//td[contains(.,'класс')]/following-sibling::td[contains(.,'городской')]"));
            doAssert(favFor.getText().contains("городской"), "Made for... is correct", "Made for... is wrong");
        }

        if (madeFor.equals("mountain")) {
            Label favFor = new Label(By.xpath("//td[contains(.,'класс')]/following-sibling::td[contains(.,'горный')]"));
            doAssert(favFor.getText().contains("горный"), "Made for... is correct", "Made for... is wrong");
        }
        if (wheelRadius.equals("26")) {
            doAssert(wheelRadius.equals(browser.getDriver().findElement(By.xpath("//td[contains(.,'Диаметр колёс')]/following-sibling::td[contains(.,'26')]/span")).getText().split(" ")[0]), "Wheel radius is correct", "Wheel radius is wrong");
        }

        if (wheelRadius.equals("29")) {
            doAssert(wheelRadius.equals(browser.getDriver().findElement(By.xpath("//td[contains(.,'Диаметр колёс')]/following-sibling::td[contains(.,'29')]/span")).getText().split(" ")[0]), "Wheel radius is correct", "Wheel radius is wrong");
        }

        info("All values are correct");
    }

    public void clearFavs() {
        browser.getDriver().findElement(By.id("selectAllBookmarks")).click();
        browser.getDriver().findElement(By.className("pmchk__del")).click();
        WebElement w = browser.getDriver().findElement(By.xpath("//p[contains(.,'Нет закладок')]"));
        Assert.assertNotNull(w);
    }
}
