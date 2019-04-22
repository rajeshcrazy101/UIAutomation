package BaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class PageBase {
 WebDriver driver;

    public By setValueForLocatorAndValue(String element,String value){
        if (element.equalsIgnoreCase("xpath")){
            return By.xpath(value);
        }else if (element.equalsIgnoreCase("className")){
            return By.className(value);
        }else if (element.equalsIgnoreCase("id")){
            return By.id(value);
        }else if (element.equalsIgnoreCase("css")){
            return By.cssSelector(value);
        }
        return null;
    }

    public WebElement findElement(String locator,String value){
        return driver.findElement(setValueForLocatorAndValue(locator,value));
    }

    public List<WebElement> findElements(String locator,String value){
        return driver.findElements(setValueForLocatorAndValue(locator,value));
    }

    public void EnterValueOnTextBox(WebElement element,String str){
        element.click();
        element.clear();
        element.sendKeys(str);
    }

    public void clickButton(String locator,String value){
        driver.findElement(setValueForLocatorAndValue(locator,value)).click();
    }

    public void waitForElementToClickable(String locator,String value){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(setValueForLocatorAndValue(locator,value)));
    }

    public void waitForElementToVisible(String locator,String value){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(setValueForLocatorAndValue(locator,value)));
    }

    public boolean isElementPresent(String locator,String value){
        return driver.findElement(setValueForLocatorAndValue(locator,value)).isDisplayed();
    }


}
