package testclass.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testclass.pop.BaraholkaPage;
import testclass.pop.MainPage;
import webdriver.BaseTest;

import static testclass.pop.MainPage.lblMarket;

/**
 * Created by Alexandr.Vershok on 4/1/2017.
 */
public class PublishMarketRequestTest extends BaseTest{
    private String model;
    private String messageBody;
    private String price;
    private String name;
    private String pass;

    @BeforeTest
    @Parameters({"Name", "Pass", "Smartphone", "MessageBody", "Price"})
    public void setUp(String name, String pass, String model, String messageBody, String price){
        this.model = model;
        this.messageBody = messageBody;
        this.price = price;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void runTest(){
        logStep("Logging in");
        MainPage mainPage = new MainPage();
        mainPage.login(name, pass);

        logStep("Opening Market");
        mainPage.open(lblMarket);

        logStep("Creating new request");
        BaraholkaPage baraholkaPage = new BaraholkaPage();
        baraholkaPage.createNewRequest(model, messageBody, price);

        logStep("Check request details are correct");
        baraholkaPage.checkRequestDetails(model, messageBody, price);


    }




}
