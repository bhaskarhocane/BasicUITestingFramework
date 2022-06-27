package pageObjects;

import base.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpSignInPO extends UIHelper {

    public SignUpSignInPO(WebDriver driver) {
        this.driver = driver;
    }

    //RTV -> runTimeVaraible
    String commonFieldXpath = "//*[@formcontrolname='RTV']";
    By btn_signUp = By.xpath("//button[text()=' Sign up ']");
    By btn_signIn = By.xpath("//button[text()=' Sign in ']");

    public SignUpSignInPO enterValueInField(String field, String value) {
        waitAndFindElement(By.xpath(
                commonFieldXpath.replace("RTV", field.toLowerCase()))).sendKeys(value);
        return this;
    }

    public HomePO clickSignUp() {
        click(btn_signUp);
        staticWait(3);
        return new HomePO(driver);
    }

    public HomePO clickSignIn() {
        click(btn_signIn);
        return new HomePO(driver);
    }

    public HomePO signUp(String username, String email, String password) {
        return enterValueInField("username", username)
                .enterValueInField("email", email)
                .enterValueInField("password", password)
                .clickSignUp();
    }

    public HomePO signIn(String email, String password) {
        return enterValueInField("email", email)
                .enterValueInField("password", password)
                .clickSignIn();
    }

}
