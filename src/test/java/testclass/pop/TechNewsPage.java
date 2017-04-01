package testclass.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Alexandr.Vershok on 3/30/2017.
 */
public class TechNewsPage extends BaseForm {
    Pattern pattern = Pattern.compile("/(\\w\\.)|(\\w+(\\s|\\.)\\w+)/u");

    private TextBox openSearch = new TextBox(By.name("query"), "Search bar");
    private TextBox iSearchBar = new TextBox(By.className("search__input"), "Iframe search bar");
    private Label articleLink = new Label(By.xpath("//div[@id='search-page']//div[@class='news__data']//a"), "Article link");
    private Label articleName = new Label(By.xpath("//ul[@class='search__results']//div[@class='news__title']"));
    private Label newsDate = new Label(By.className("news__time"), " News Date");


    public TechNewsPage() {
        super(By.xpath("//li[@class='project-navigation__item project-navigation__item_primary project-navigation__item_active']//span[contains(text(),'Технологии')]"), "Technology News Page");
    }

    public void searchForNews(String searchString) {
        info("Typing search query");
        openSearch.setText(" ");
        WebElement searchFrame = browser.getDriver().findElement(By.className("modal-iframe"));
        browser.getDriver().switchTo().frame(searchFrame);
        iSearchBar.setText(searchString);

    }

    public void saveData(String searchString) {
        try {
            FileWriter fw = new FileWriter(searchString + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            info("Getting article links");
            List<String> linkList = new ArrayList<>();
            List<WebElement> webList = new ArrayList<>();
            for (WebElement webElement : webList = browser.getDriver().findElements(articleLink.getLocator())) {
                linkList.add(webElement.getAttribute("href"));
            }

            info("Getting article names");
            List<String> namesList = new ArrayList<>();
            for (WebElement webElement : webList = browser.getDriver().findElements(articleName.getLocator())) {
                namesList.add(webElement.getText());
            }

            info("Getting article dates");
            List<String> datesList = new ArrayList<>();
            for (WebElement webElement : webList = browser.getDriver().findElements(newsDate.getLocator())) {
                datesList.add(webElement.getText());
            }

            doAssert((linkList.size() == datesList.size()) && (linkList.size() == namesList.size()), "List sizes match", "List sizes dont match");

            info("Writing data to file");
            for (int i = 0; i < linkList.size(); i++) {

                bw.write(namesList.get(i));
                bw.newLine();
                bw.write(linkList.get(i));
                bw.newLine();
                bw.write(datesList.get(i));
                bw.newLine();
            }

            bw.close();
            fw.close();
            info("Check the file under 'PROJECT_ROOT\\target' folder");

            FileReader fr = new FileReader(searchString + ".txt");
            BufferedReader br = new BufferedReader(fr);

            logStep("Read the data");
            String s = " ";
            List<String> newsList = new ArrayList<>();
            for (int count = 0; s != null; count++) {
                s = br.readLine();
                if (s == null) continue;
                newsList.add(s);
            }

            info("Check that all news saved");

            doAssert(newsList.size() == (namesList.size() + linkList.size() + datesList.size()), "Number of saved news matches search result", "Some news missing");
            info("News links:");
            for (String link : newsList) {

                if (link.contains("http")) {
                    info(link);
                }
            }

            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
