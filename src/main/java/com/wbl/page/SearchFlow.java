package com.wbl.page;

import com.wbl.base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class SearchFlow extends BasePage {
    public SearchFlow(WebDriver driver){super(driver);}

    //Function searches for the item and takes the screen shot of the same and
    // places in current directory
    public  static String searchValidate(WebDriver driver,String item) {
        String output="";
        search(driver,item);
        takeScreenshot(driver,item);
        List<WebElement> li=driver.findElements(By.xpath("//div[@class='search-result-product-title gridview']"));
        for (WebElement we:li) {
            WebElement el=we.findElement(By.tagName("a"));
            output = output + el.getText()+"\n";
        }
        return output;

    }

    //This Method navigates back to Homepage
    public static void beforeTest(WebDriver driver ,String path){
        driver.navigate().to(path);
    }



    public  static void search(WebDriver driver,String item){
        WebDriverWait wait = new WebDriverWait(driver, 300);
        WebElement search=driver.findElement(By.id("global-search-input"));
        search.sendKeys(item);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("global-search-submit")));
        WebElement searchButton=driver.findElement(By.id("global-search-submit"));
        searchButton.click();
        //you can play with the time integer  to wait for longer than 15 seconds.`
        wait.until(ExpectedConditions.titleContains(item+" - Walmart.com"));
        if(driver.getTitle().equals(item+" - Walmart.com")){
            System.out.println("In search page for "+item);
        }
    }

    public static void takeScreenshot(WebDriver driver,String item){
        String destLocation="."+item+".png";
        TakesScreenshot shot=(TakesScreenshot)driver;
        File shotFile=shot.getScreenshotAs(OutputType.FILE);
        File destFile=new File(destLocation);
        try {
            FileUtils.copyFile(shotFile, destFile);
            System.out.println("Screenshot : "+destLocation);
        }catch(IOException ie)
        {
            System.out.println("Exception caught");
        }
    }

    public static String addToCartFirstItem(WebDriver driver,String item) {
        search(driver, item);
        WebElement element = driver.findElement(By.xpath("//div[@class='search-result-product-title gridview']"));
        String brand=driver.findElement(By.xpath("//*[@id=\"searchProductResult\"]/ul/li[1]/div/div[2]/div[5]/div")).getText();
        String itemSelected=element.findElement(By.tagName("a")).getText();
        element.click();
        //check if correct item is selected
        if(driver.getTitle().equalsIgnoreCase(brand+" - "+itemSelected+" - Walmart.com"))
            System.out.println("In right page");

        WebElement check=driver.findElement(By.xpath("//div[@class='Grid']/div[2]"));
        //when option is present to select a color
        if(check.findElement(By.xpath("//div[@role='radiogroup']/span[1]"))!=null) {
            check.findElement(By.xpath("//div[@role='radiogroup']/span[1]")).click();
        }
        WebElement btn=check.findElement(By.xpath("//*[contains(text(),'Add to Cart')]"));
        btn.click();

        WebDriverWait wait = new WebDriverWait(driver, 215); //you can play with the time integer  to wait for longer than 15 seconds.`
        wait.until(ExpectedConditions.titleContains("Item added to cart - Walmart.com"));
        WebElement ele=driver.findElement(By.xpath("//*[@id=\"cart-root-container-content-skip\"]/div/div/div[2]/div/div/div/div/div[1]/h3/div/span[1]/span"));
        if(ele.getText().contains("You just added 1 item"))
        return "Success";
        return "Failure";
    }

}
