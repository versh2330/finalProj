package testclass.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testclass.pop.MainPage;
import testclass.pop.ProfilePage;
import webdriver.BaseTest;


/**
 * Created by Alexandr.Vershok on 3/28/2017.
 */
public class ProfileEditTest extends BaseTest {
    private String name;
    private String pass;
    private String city;
    private String bDay;
    private String bMonth;
    private String bYear;



    @BeforeTest
    @Parameters({"Name", "Pass", "City", "Day", "Month", "Year"})
    public void setUp(String name, String pass, String city, String bDay, String bMonth, String bYear) {
        this.name = name;
        this.pass = pass;
        this.city = city;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;

    }

    @Override
    public void runTest(){

        logStep("Logging in");
        MainPage mainPage = new MainPage();
        mainPage.login(name, pass);

        logStep("Open profile page");
        mainPage.open(MainPage.lblProfileName);

        logStep("Edit profile details");
        ProfilePage profilePage = new ProfilePage();
        profilePage.openEditProfilePage();
        profilePage.editPersonalData(city, bDay, bMonth, bYear);

        logStep("CHeck that profile data is correct");
        profilePage.checkDataChanged(city, bDay, bMonth, bYear);
    }


}
