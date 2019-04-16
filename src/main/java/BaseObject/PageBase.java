package BaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageBase extends BaseClass {
    public PageBase(WebDriver driver) {
        super(driver);
    }

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
}
