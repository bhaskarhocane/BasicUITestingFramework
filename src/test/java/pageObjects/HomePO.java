package pageObjects;

import base.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePO extends UIHelper {

    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    By link_signUp = By.xpath("//*[text()=' Sign up ']");
    By link_signIn = By.xpath("//*[text()=' Sign in ']");
    By link_newArticle = By.xpath("//*[contains(text(),'New Article')]");
    By tab_globalFeed = By.xpath("//*[text()=' Global Feed ']");
    By link_userAvtar = By.xpath("(//*[contains(@class,'navbar')]//li)[last()]//a");


    public SignUpSignInPO clickSignUp() {
        click(link_signUp);
        return new SignUpSignInPO(driver);
    }

    public SignUpSignInPO clickSignIn() {
        click(link_signIn);
        return new SignUpSignInPO(driver);
    }

    public ArticlePO clickNewArticle() {
        click(link_newArticle);
        return new ArticlePO(driver);
    }

    public HomePO clickGlobalFeedTab() {
        click(tab_globalFeed);
        return this;
    }

    public ArticlePO openArticle(String articleName) {
        click(By.xpath("//*[@class='preview-link']//h1[text()='" + articleName + "']"));
        return new ArticlePO(driver);
    }

    public UserProfilePO clickOnUserAvtar() {
        click(link_userAvtar);
        return new UserProfilePO(driver);
    }


}
