package test;

import base.UIHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ArticlePO;
import pageObjects.HomePO;
import pageObjects.SignUpSignInPO;
import pageObjects.UserProfilePO;
import utils.Utils;

public class ArticleScenarios extends UIHelper {

    String url = "https://candidatex:qa-is-cool@qa-task.backbasecloud.com/";
    String username, email, password;

    void generateData() {
        Utils utils = new Utils();
        username = utils.generateRandomString(6);
        email = username + "@" + utils.generateRandomString(4) + ".com";
        password = utils.generateRandomString(5);
    }

    @BeforeClass
    public void registerUser() {
        generateData();
        launchBrowser();
        goToUrl(url);
        HomePO homePO = new HomePO(driver);
        SignUpSignInPO signUpSignInPO = homePO.clickSignUp();
        signUpSignInPO.signUp(username, email, password);
        driver.quit();
    }

    @BeforeMethod
    public void doLogin() {
        launchBrowser();
        goToUrl(url);
        HomePO homePO = new HomePO(driver);
        SignUpSignInPO signUpSignInPO = homePO.clickSignIn();
        signUpSignInPO.signIn(email, password);
    }

    @Test(description = "Check Validation at Add Article", enabled = false)
    public void tc_00() {
        //Bug
    }

    @Test(description = "Post Article with single tag")
    public void tc_01() {

        Utils utils = new Utils();
        String[] tags = {"a"};
        String name = "article with single tag" + utils.generateRandomString(3);
        String about = "about";
        String article = "Article";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags);
        articlePO.verifyArticle(name, article, tags);

    }

    @Test(description = "Post Article with multiple tags")
    public void tc_02() {
        Utils utils = new Utils();
        String[] tags = {"a", "ab", "abc"};
        String name = "article with multi tags" + utils.generateRandomString(3);
        String about = "about";
        String article = "Article";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags);
        articlePO.verifyArticle(name, article, tags);
    }

    @Test(description = "Edit Article but don't publish it")
    public void tc_03() {
        Utils utils = new Utils();
        String[] tags = {"tag"};
        String[] editedTag = {"tag_edit"};
        String name = "article" + utils.generateRandomString(3);
        String editedName = name + "_Edit";
        String about = "about";
        String article = "Article";
        String editedArticle = article + "_Edit";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags)
                .clickEditArticle()
                .enterValueInField("title", "_Edit")
                .enterValueInField("body", "_Edit")
                .enterTags(editedTag)
                .gotoHomePage();
        homePO.clickGlobalFeedTab()
                .openArticle(name);
        articlePO.verifyArticle(name, article, tags);
    }

    @Test(description = "Edit Article")
    public void tc_04() {
        Utils utils = new Utils();
        String[] tags = {"tag"};
        String[] editedTag = {"tag_edit"};
        String name = "article" + utils.generateRandomString(3);
        String editedName = name + "_Edit";
        String about = "about";
        String article = "Article";
        String editedArticle = article + "_Edit";
        String[] editTags = {tags[0], editedTag[0]};

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags)
                .clickEditArticle()
                .addEditArticle("_Edit", "_Edit", "_Edit", editedTag)
                .verifyArticle(editedName, editedArticle, editTags);
    }

    @Test(description = "Read Article(non-author user)")
    public void tc_05() {
        Utils utils = new Utils();
        String[] tags = {"tag"};
        String name = "article" + utils.generateRandomString(3);
        String about = "about";
        String article = "Article";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags);
        staticWait(3);
        driver.quit();
        driver = launchBrowser();
        goToUrl(url);
        homePO = new HomePO(driver);
        homePO.clickGlobalFeedTab();
        articlePO = homePO.openArticle(name);
        Assert.assertFalse(articlePO.isEditBtnDisplayed());
    }

    @Test(description = "delete Article")
    public void tc_06() {
        Utils utils = new Utils();
        String[] tags = {"tag"};
        String name = "article" + utils.generateRandomString(3);
        String about = "about";
        String article = "Article";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags);
        articlePO.clickDeleteArticleBtn();
        staticWait(2);
        UserProfilePO userProfilePO = homePO.clickOnUserAvtar();
        staticWait(2);
        Assert.assertTrue(userProfilePO.isNoArticleDisplayed());
    }

    @Test(description = "Post Article with single tag")
    public void tc_07() {

        Utils utils = new Utils();
        String[] tags = {"tag", "tag", "tag"};
        String[] expcetedtag = {"tag"};
        String name = "article with single tag" + utils.generateRandomString(3);
        String about = "about";
        String article = "Article";

        HomePO homePO = new HomePO(driver);
        ArticlePO articlePO = homePO.clickNewArticle();
        articlePO.addEditArticle(name, about, article, tags);
        articlePO.verifyArticle(name, article, expcetedtag);

    }

}
