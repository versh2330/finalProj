package testclass.pop;

import webdriver.BaseForm;
import org.openqa.selenium.By;
import webdriver.elements.Label;

/**
 * Created by Alexandr.Vershok on 4/2/2017.
 */
public class CatalogPage extends BaseForm {

    private Label lblSport = new Label(By.xpath("//div[@class='catalog-navigation']//span[contains(.,'спорт')]/span"), "Sport category");
    private Label lblBikesSubcategory = new Label(By.xpath("//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'Велосипеды')]"), "Bikes subcategory");
    private Label lblBikes = new Label(By.xpath("//div[@class='catalog-navigation-list__aside-list']//div[contains(text(),'Велосипеды')]/following-sibling::div//span[contains(text(),'Велосипеды')]"),"Bikes catalog");

    public CatalogPage() { super(By.className("catalog-navigation__title"), "Catalog page"); }

    public void openCategory(){
        lblSport.click();
        lblBikesSubcategory.click();
        lblBikes.click();
    }



}
