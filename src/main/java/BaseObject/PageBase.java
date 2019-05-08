package BaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * Creating or add a common methods which will return a page elements/DOM elements.
* */
public class PageBase {
 RemoteWebDriver driver;

 /**
  * setting a elements for DOM.
  * */
 public By setValueForLocatorAndValue(String element,String value){
        By by=null;
        if (element.equalsIgnoreCase("xpath")){
            return By.xpath(value);
        }else if (element.equalsIgnoreCase("className")){
            return By.className(value);
        }else if (element.equalsIgnoreCase("id")){
            return By.id(value);
        }else if (element.equalsIgnoreCase("css")){
            return By.cssSelector(value);
        }else if ((element.equalsIgnoreCase("partialLinkText"))) {
            return By.partialLinkText(value);
        }else if (element.equalsIgnoreCase("linkText")){
            return By.linkText(value);
        }else if (element.equalsIgnoreCase("tag")){
            return By.tagName(value);
        }
        return by;
    }

    /**
     * Find element method
     * */
    public WebElement findElement(String locator,String value){
        return driver.findElement(setValueForLocatorAndValue(locator,value));
    }

    /**
     * Find elements method
     * */
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

    public void mouseHover(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void clickAndHold(WebElement element){
        Actions actions=new Actions(driver);
        actions.clickAndHold().build().perform();
    }

    public void dragAndDrop(WebElement source,WebElement dec){
        Actions actions=new Actions(driver);
        actions.dragAndDrop(source,dec).build().perform();
        actions.release();
    }

    public WebElement findAndReplace(String locator,String oldValue,String newValue){
        return findElement(locator,oldValue.replace("$",newValue));
    }

    public void selectDropDown(WebElement element,String value){
        Select select = (Select) element;
        select.selectByVisibleText(value);
    }


    public void scrollDown(int scrollTimes){
        int start = 0;
        int end = 1000;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i =0 ; i < scrollTimes; i++){
            js.executeScript("window.scrollTo("+start+", "+end+");");
            start=end;
            end= end+500;
        }
    }

    public void scrollToViewElement(WebElement element){
//        int i = 5;
//        while(element.isDisplayed() && i != 0 ){
//            scrollDown(10);
//            i--;
//        }
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

}
