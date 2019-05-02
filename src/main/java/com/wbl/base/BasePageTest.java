package com.wbl.base;

import com.wbl.common.Utils;
import com.wbl.common.WebUIDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BasePageTest {
    protected WebDriver driver;
    @BeforeSuite
    public void beforesuite() throws FileNotFoundException, IOException {
        System.out.println("Implementing BeforeSuite");
        Utils ut=new Utils();
        this.driver= WebUIDriver.getDriver(ut);
        driver.manage().window().maximize() ;
    }


    @AfterSuite
    public void aftersuite(){
        System.out.println("Implementing AfterSuite");
        driver.close();

    }

}
