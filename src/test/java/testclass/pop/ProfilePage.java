package testclass.pop;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Button;
import webdriver.elements.TextBox;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by Alexandr.Vershok on 3/28/2017.
 */
public class ProfilePage extends BaseForm {
    private Label lblMyMessages = new Label(By.xpath("//a[contains(text(),'Мои сообщения')]"), "Open private messages ");
    private Label lblEditProfileDetails = new Label(By.xpath("//a[contains(text(),'Редактировать личные данные')]"), "Edit profile details");
    private Label lblSendMessages = new Label(By.xpath("//li[@id='l_compose']/a"), "Write message form");
    private Label lblPersonalMessages = new Label(By.xpath("//h1[contains(.,'Личные сообщения')]"), "Personal Messages page");
    private Label lblProfileName = new Label(By.xpath("//p[@class='user-name']/a"), "Open Profile");
    private Button btnSend = new Button(By.xpath("//button[@value='Отправить']"), "Send button");
    private TextBox txbRecipient = new TextBox(By.id("compose_uname"), "To... field");
    private TextBox txbSubject = new TextBox(By.id("compose_subject"), "Subject field");
    private TextBox txbMessageBody = new TextBox(By.id("compose_text"), "Message body");
    private Label lblOutcoming = new Label(By.xpath("//li[@id='l_sentbox']/a"), "Outcoming messages folder");
    private Label lblSelectAll = new Label(By.id("select-msg-all"), "Select All messages");
    private Label lblDelete = new Label(By.xpath("//span[contains(.,'удалить')]"), "Delete button");
    private Label lblEmtyOutbox = new Label(By.className("pmmsg__empty"), "Outbox empty");
    private Label exit = new Label(By.className("exit"), "Exit button");
    private Button btnLogin = new Button(By.xpath("//button[contains(.,'Войти')]"), "Login button");
    private TextBox txbCity = new TextBox(By.name("city"), "City field");
    private TextBox txbBDay = new TextBox(By.name("user_bday"), "Birth day");
    private TextBox txbBMonth = new TextBox(By.name("user_bmonth"), "Month of birth");
    private TextBox txbBYear = new TextBox(By.name("user_byear"), "Year of birth");
    private Button btnSaveProfile = new Button(By.xpath("//div[@id='tab-personal']//button[@type='submit']"), "Save profile");
    private Label lblCityName = new Label(By.xpath("//dt[contains(text(),'Из города')]/following-sibling::dd[1]"));
    private Label lblBirthDate = new Label(By.xpath("//dt[contains(text(),'День рождения')]/following-sibling::dd[1]"));

    private String messageBody;
    private String messageRecipient;
    private String messageSubject;

    public ProfilePage() {
        super(By.id("notedit"), "Profile Page");
    }

    public void sendMessage(String messageRecipient, String messageBody, String messageSubject) {
        this.messageRecipient = messageRecipient;
        this.messageBody = messageBody;
        this.messageSubject = messageSubject;
        lblMyMessages.isPresent(1);
        lblMyMessages.isPresent();
        info("Opening messages");
        lblMyMessages.click();
        lblPersonalMessages.isPresent();
        lblSendMessages.isPresent();
        lblSendMessages.click();
        btnSend.waitForIsElementPresent();
        info("Composing a message");
        txbRecipient.setText(messageRecipient);
        txbSubject.setText(messageSubject);
        txbMessageBody.setText(messageBody);
        info("Sending message");
        btnSend.click();
        info("Checking outcoming messages folder");
        lblOutcoming.waitForIsElementPresent();
        lblOutcoming.click();
        String actualRecipient = new Label(By.xpath("//div[@class='lpm-author']/a")).getText();
        assertEquals(actualRecipient, messageRecipient);
        String actualSubject = new Label(By.xpath("//div[@class='lpm-subj']/a")).getText();
        assertEquals(actualSubject, messageSubject);
        info("Message Recipient and Subject are correct");
        info("Deleting all messages");
        lblSelectAll.click();
        lblDelete.isPresent(1);
        lblDelete.click();
        Alert alert = browser.getDriver().switchTo().alert();
        alert.accept();
        info("Checking no messages left");
        lblEmtyOutbox.isPresent(1);
        lblEmtyOutbox.isPresent();
    }

    public void openEditProfilePage() {
        lblEditProfileDetails.isPresent(1);
        lblEditProfileDetails.isPresent();
        lblEditProfileDetails.click();
    }

    public void editPersonalData(String city, String day, String month, String year) {
        info("Entering personal data");
        txbCity.setText(city);
        txbBDay.click();
        txbBDay.type(day);
        txbBMonth.type(month);
        txbBYear.type(year);
        info("Saving changes");
        btnSaveProfile.isPresent();
        btnSaveProfile.click();
    }

    public void checkDataChanged(String city, String day, String month, String year) {
        String birthDate = day + " " + month + " " + year;
        lblProfileName.click();
        info("Checking profile data");
        assertEquals(city, lblCityName.getText());
        assertTrue(lblBirthDate.getText().contains(birthDate));
        info("Profile data saved correctly");
    }

    public void logout() {
        exit.click();
        btnLogin.isPresent(1);
        btnLogin.isPresent();
        info("Logout successful");
    }
}
