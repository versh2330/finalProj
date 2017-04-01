package testclass.tests;

import org.junit.Before;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testclass.pop.MainPage;
import testclass.pop.TechNewsPage;
import webdriver.BaseTest;

/**
 * Created by Alexandr.Vershok on 3/30/2017.
 */
public class SaveDataFromNewsTest extends BaseTest {
    private String searchString;



    @BeforeTest
    @Parameters({"SearchString"})
    public void setUp(String searchString) {
    this.searchString = searchString;

    }


    @Override
    public void runTest() {
        logStep("Opening news page");
        MainPage mainPage = new MainPage();
        mainPage.open(MainPage.lblTechnoNews);

        logStep("Enter the query");
        TechNewsPage techNewsPage = new TechNewsPage();
        techNewsPage.searchForNews(searchString);

        logStep("Save news list");
        techNewsPage.saveData(searchString);

    }

}
