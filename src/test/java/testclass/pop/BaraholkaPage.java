package testclass.pop;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by Alexandr.Vershok on 4/1/2017.
 */
public class BaraholkaPage extends BaseForm {

    private Button btnCreateMessage = new Button(By.className("b-btn-fleamarket__1"), "'Create message' button");
    private Label lblCheckHeader = new Label(By.xpath("//div[@class='m-title']//a[2]"));
    private Label lblCheckBody = new Label(By.xpath("//div[@id='last']/div/p"));
    private Label lblCheckPrice = new Label(By.className("price-primary"));
    private Label lblNewRequest = new Label(By.xpath("//div[@id='baraholka-form_container']/div/div[contains(.,'Новое объявление')]"), "'New request' page open");
    private Label lbliBuy = new Label(By.xpath("//span[@class='i-checkbox__faux']/following-sibling::span[contains(.,'Куплю')]/preceding-sibling::span"), "Buy checkbox");
    private Label lblCategorySelect = new Label(By.xpath("//div[@class='input-style__wrapper baraholka-form__input-wrapper']/div"), "Choose request category");
    private Label lblSmartPhones = new Label(By.xpath("//optgroup[@label='Телефоны. Смартфоны']/option[@value='2']"), "Smartphones category");
    private TextBox txbMessageHeader = new TextBox(By.xpath("//input[@name='subject']"), "Message subject");
    private TextBox txbMessageBody = new TextBox(By.xpath("//textarea[@name='message']"), "Message Body");
    private TextBox txbPrice = new TextBox(By.id("topic_price"), "Price field");
    private Label lblBuy = new Label(By.xpath("//span[@class='i-checkbox__faux']/following-sibling::span[contains(.,'Торг уместен')]/preceding-sibling::span"), "'Price negotiable' checkbox");
    private Button btnAddMessage = new Button(By.id("btnFleaMarketPostingSubmitEnabled"),"Publish request");

    public BaraholkaPage(){
        super(By.xpath("//nav[@class='b-top-navigation']//span[contains(text(),'Барахолка')]"), "Baraholka");
    }

    public void createNewRequest(String header, String messageBody, String price){

        btnCreateMessage.click();
        lblNewRequest.waitForIsElementPresent();
        lbliBuy.click();
        info("clicking category");
        lblCategorySelect.click();
        lblSmartPhones.waitForIsElementPresent();
        lblSmartPhones.click();
        txbMessageHeader.setText(header);
        txbMessageBody.setText(messageBody);
        txbPrice.setText(price);
        lblBuy.click();
        btnAddMessage.waitForIsElementPresent();
        info("All fields set. Publishing the request");
        btnAddMessage.click();
    }

    public void checkRequestDetails(String header, String messageBody, String price){

        lblCheckHeader.waitForIsElementPresent();
        doAssert(lblCheckHeader.getText().equals(header), "Request header is correct", "Request header is wrong");
        doAssert(lblCheckBody.getText().equals(messageBody), "Request body is correct", "Request body is wrong");
        doAssert(lblCheckPrice.getText().split(" ")[0].equals(price), "Request price is correct", "Request price is wrong");
    }

}
