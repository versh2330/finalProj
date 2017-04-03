package testclass.pop;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by Alexandr.Vershok on 3/28/2017.
 */
public class MainPage extends BaseForm {
    private Button btnLogin = new Button(By.xpath("//div[@id='userbar']//div[contains(text(),'Вход')]"), "Login button");
    public static Label lblMarket = new Label(By.xpath("//nav[@class='b-top-navigation']//span[contains(text(),'Барахолка')]"), "Catalog page");
    public static Label lblTechnoNews = new Label(By.xpath("//h2/a[contains(text(),'Технологии')]"), "News page");
    public static Label lblCatalog = new Label(By.xpath(".//*[@class=\"b-main-navigation\"]/li/a[contains(.,\"Каталог\")]"), "Catalog link");
    public static Label lblProfileName = new Label(By.xpath("//p[@class='user-name']/a"), "Open Profile");
    private Label lblLogout = new Label(By.xpath("//[@id='userbar']//a[contains(text(),'Выйти')]"), "Logout");
    private TextBox txbName = new TextBox(By.xpath("//input[@data-field='login'][@type='text']"), "Name");
    private TextBox txbPass = new TextBox(By.xpath("//input[@class='auth-box__input']/following-sibling::a[contains(.,'Не помню')]/preceding-sibling::input"), "Password");
    private Button btnSubmit = new Button(By.xpath("//button[contains(.,'Войти')]"), "Submit");
    private String name;
    private String pass;

    public MainPage() {
        super(By.xpath("//a[@href='https://www.onliner.by/']"), "Main Page");
    }


    public void login(String name, String pass) {
        this.name = name;
        this.pass = pass;
        btnLogin.click();
        txbName.isPresent(1);
        txbName.isPresent();
        info("Login form is present");
        txbName.setText(name);
        txbPass.setText(pass);
        btnSubmit.click();
        lblProfileName.isPresent(1);
        lblProfileName.isPresent();

    }

    public void open(Label label) {
        info("Opening the page");
        if (label.equals(lblProfileName)) {
            lblProfileName.click();
        } else if (label.equals(lblTechnoNews)) {
            lblTechnoNews.click();
        } else if (label.equals(lblMarket)) {
            lblMarket.click();
        }
        else if (label.equals(lblCatalog)) {
            lblCatalog.click();
        }
    }

}
