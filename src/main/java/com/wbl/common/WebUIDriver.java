package com.wbl.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebUIDriver {
    public WebUIDriver(Utils ut){
        getDriver(ut);
    }

    public static WebDriver getDriver(Utils ut){
        WebDriver driver=null;
        switch(ut.browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","chromedriver.exe");
                driver=new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.firefox.marionette","geckodriver.exe");
                driver=new FirefoxDriver();
                break;
            default :
                System.setProperty("webdriver.chrome.driver","chromedriver.exe");
                driver=new ChromeDriver();
                break;
        }
        driver.get(ut.url);
        return driver;
    }

}
