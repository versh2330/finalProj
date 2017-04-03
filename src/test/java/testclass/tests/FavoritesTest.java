package testclass.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testclass.pop.CatalogPage;
import testclass.pop.MainPage;
import testclass.pop.ResultsPage;
import webdriver.BaseTest;

/**
 * Created by Alexandr.Vershok on 4/2/2017.
 */
public class FavoritesTest extends BaseTest {
    private String name;
    private String pass;
    private String year;
    private String madeFor;
    private String wheelRadius;



    @BeforeTest
    @Parameters({"Name", "Pass", "Year", "MadeFor", "WheelRadius"})
    private void setUp(String name, String pass, String year, String madeFor, String wheelRadius) {
        this.name = name;
        this.pass = pass;
        this.year = year;
        this.madeFor = madeFor;
        this.wheelRadius = wheelRadius;

    }

    public void runTest() {

        logStep("Open main page");
        MainPage mainPage = new MainPage();
        mainPage.login(name, pass);

        logStep("Open Catalog");
        mainPage.open(MainPage.lblCatalog);

        logStep("Open Bikes catalog page");
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.assertIsOpen();
        info("Catalog page open");
        catalogPage.openCategory();


        logStep("Filter results");
        ResultsPage resultsPage = new ResultsPage();
        resultsPage.setUpFilter(year, madeFor, wheelRadius);

        logStep("Add to favorite");
        resultsPage.addToFavs();

        logStep("Check details are correct");
        resultsPage.checkDetails();

        logStep("Find the bike just added");
        resultsPage.findLatestAdded();

        logStep("Clearing favs list");
        resultsPage.clearFavs();
        info("Favs cleared");

    }

}
