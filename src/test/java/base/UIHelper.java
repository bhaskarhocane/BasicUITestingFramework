package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIHelper extends WebDriverHelper {

    public WebElement waitAndFindElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        executeJS("arguments[0].style.border='2px solid red'", webElement);
        return webElement;
    }

    public void executeJS(String script, WebElement webElement) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script, webElement);
    }

    public Object executeJS(String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script);
    }

    public UIHelper goToUrl(String url) {
        driver.get(url);
        return this;
    }

    public UIHelper click(By locator) {
        waitAndFindElement(locator).click();
        return this;
    }

    public UIHelper sendText(By locator, String text) {
        waitAndFindElement(locator).sendKeys(text);
        return this;
    }

    public String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    public void waitForPageToLoad() {
        while (!executeJS("return document.readyState").toString().equals("complete")) {
            staticWait(3);
        }
    }

    public boolean isElementDisplayed(By by) {
        waitForPageToLoad();
        try {
            if (driver.findElement(by).isDisplayed())
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void staticWait(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
