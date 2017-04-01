package testclass.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testclass.pop.MainPage;
import testclass.pop.ProfilePage;
import webdriver.BaseTest;

/**
 * Created by Alexandr.Vershok on 3/28/2017.
 */
public class SendMessageTest extends BaseTest {
    private String name;
    private String pass;
    private String messageBody;
    private String messageRecipient;
    private String messageSubject;


    @BeforeTest
    @Parameters({"Name", "Pass", "Body", "Recipient", "Subject"})
    public void setUp(String name, String pass, String messageBody, String messageRecipient, String messageSubject) {
        this.name = name;
        this.pass = pass;
        this.messageBody = messageBody;
        this.messageRecipient = messageRecipient;
        this.messageSubject = messageSubject;

    }

    @Override
    public void runTest() {

        logStep("Logging in");
        MainPage mainPage = new MainPage();
        mainPage.login(name, pass);

        logStep("Entering Profile page");
        mainPage.open(MainPage.lblProfileName);

        logStep("Opening Messages");
        ProfilePage profilePage = new ProfilePage();
        profilePage.sendMessage(messageRecipient,messageBody,messageSubject);

        logStep("Logging out");
        profilePage.logout();
    }


}
