package pageObjects;

import base.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePO extends UIHelper {

    By text_NoArticleYet = By.xpath("//app-article-list//*[contains(text(),' No articles are here... yet.')]");

    public UserProfilePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNoArticleDisplayed() {
        return isElementDisplayed(text_NoArticleYet);
    }


}
