package com.wbl.page;

import com.wbl.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Function returns Title of the webpage wallmart.com
    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    //Checks the logo present in top right.
    //Tooltiptooltip is a text that appears when a mouse hovers over an object like a link
    public static String getToolTipText(WebDriver driver) {
        WebElement logo = driver.findElement(By.xpath("//a[@id='vh-home-link']"));
        return logo.getAttribute("title") + "---" + logo.getAttribute("href");
    }

    //Function returns the footer message of the webpage
    public static String getFooterMessage(WebDriver driver) {
        WebElement footer1 = driver.findElement(By.xpath("//div[@class='e_a n_a n_f z_a e_f e_t']/span[@class='v_a v_j']"));
        WebElement footer2 = driver.findElement(By.xpath("//div[@class='e_a n_a n_f z_a e_f e_t']/span[@class='v_a v_g v_j']"));
        return ((WebElement) footer1).getText() + "---" + footer2.getText();
    }

    //Function returns all the menuitems present in the dropdown which is located
    //beside the  search button
    public static String[] selectDropDown(WebDriver driver) {
        driver.findElement(By.xpath("//button[@class='f_a ar_f']")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement elements = driver.findElement(By.xpath("//div[@id='SearchDropdown-list']/div[@class='e_a ap_f e_t']"));
        return elements.getText().split("\\n");
    }


    //Function returns the sections headers which contains links
    // and is present in the footer of the webpage
    public static ArrayList<String> footerLinks(WebDriver driver) {
        ArrayList<String> list = new ArrayList<String>();
        List<WebElement> elems = driver.findElements(By.xpath("//*[@id=\"js-global-footer-wrapper\"]/div/div[2]/div/div"));
        int i = 1;
        for (WebElement ddlList : elems) {
            list.add(ddlList.findElement(By.xpath("//*[@id='js-global-footer-wrapper']/div/div[2]/div/div[" + i + "]/div")).getText());
            i++;
        }
        return list;
    }

    //Functions clicks the Feedback button and returns the text present in the window.
    public static String feedback(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 500);
        String path = "//*[@id='vf-feedback-bubble-modal']/div[1]";
        driver.findElement(By.xpath("//span[@id='vf-feedback-tab-text']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
        return driver.findElement(By.xpath(path)).getText();
    }

    //Function to check Signup to newsletters option in the footer
    public static String signupNewsletters(WebDriver driver, String email) throws InterruptedException {
        WebElement signup = driver.findElement(By.xpath("//input[@id='vf-email-input']"));
        signup.sendKeys(email);
        signup.submit();
        Thread.sleep(1000);
        String path=driver.findElement(By.xpath("//div[@class='js-footer']")).getText();
         return  path;
    }

    public static HashMap<String, String> socialMediaIcons(WebDriver driver) {
        HashMap<String, String> hmap = new HashMap<String, String>();
        WebElement element = driver.findElement(By.xpath("//*[@id='js-global-footer-wrapper']/div/div[1]/div"));
        List<WebElement> ll = element.findElements(By.tagName("a"));
        for (WebElement el : ll) {
            hmap.put(el.getAttribute("title"), el.getAttribute("href"));
        }
        return hmap;
    }

    //This Method navigates back to Homepage
    public static void beforeTest(WebDriver driver ,String path){
        driver.navigate().to(path);
    }
    public static  ArrayList<String> getTabs(WebDriver driver){
        ArrayList<String> ar=new ArrayList<String>();
        WebElement element=driver.findElement(By.id("header-bubble-links"));
        List<WebElement> ll=element.findElements(By.xpath("//span[@class='e_a y_c y_f e_g e_b e_p v_a v_h v_j']"));
        for(WebElement we:ll){
            ar.add(we.getAttribute("innerHTML"));
        }
        return ar;
    }

}
