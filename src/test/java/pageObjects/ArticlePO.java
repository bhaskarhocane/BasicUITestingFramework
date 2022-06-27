package pageObjects;

import base.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ArticlePO extends UIHelper {

    String commonFieldXpath = "//*[@formcontrolname='RTV']";

    By btn_publishArticle = By.xpath("//*[text()=' Publish Article ']");
    By input_tags = By.xpath("//*[@placeholder='Enter tags']");
    By txt_title = By.xpath("//*[@class='banner']//h1");
    By txt_article = By.xpath("//*[@class='row article-content']//p");
    By text_tags = By.xpath("//*[@class='tag-list']//li");
    By btn_editArticle = By.xpath("//*[text()=' Edit Article ']");
    By btn_delete = By.xpath("//*[text()=' Delete Article ']");
    By logo = By.xpath("//*[text()='conduit']");

    public ArticlePO(WebDriver driver) {
        this.driver = driver;
    }

    public ArticlePO enterValueInField(String field, String value) {
        waitAndFindElement(By.xpath(
                commonFieldXpath.replace("RTV", field.toLowerCase()))).sendKeys(value);
        return this;
    }


    public ArticlePO addEditArticle(String name, String about, String article, String[] tags) {
        enterValueInField("title", name)
                .enterValueInField("description", about)
                .enterValueInField("body", article)
                .enterTags(tags);
        click(btn_publishArticle);
        return this;
    }

    public ArticlePO enterTags(String[] tags) {
        for (String tag : tags)
            sendText(input_tags, tag + Keys.RETURN);
        return this;
    }


    public void verifyArticle(String name, String article, String[] tags) {
        Assert.assertEquals(getText(txt_title), name);
        Assert.assertEquals(getText(txt_article), article);
        int i = 0;
        for (WebElement tagEle : driver.findElements(text_tags)) {
            Assert.assertEquals(tagEle.getText(), tags[i]);
            i++;
        }
    }

    public HomePO gotoHomePage() {
        click(logo);
        return new HomePO(driver);
    }

    public ArticlePO clickEditArticle() {
        click(btn_editArticle);
        return this;
    }

    public boolean isEditBtnDisplayed() {
        return isElementDisplayed(btn_editArticle);
    }

    public ArticlePO clickDeleteArticleBtn() {
        click(btn_delete);
        return this;
    }

}
