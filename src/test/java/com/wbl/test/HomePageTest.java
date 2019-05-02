package com.wbl.test;

import com.wbl.base.BasePageTest;
import com.wbl.common.getTestData;
import com.wbl.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//The test sata is got from TestData/WalmartTestData.xlsx which is read using getTestData.java
//and stored in a hashmap



public class HomePageTest extends BasePageTest {
    private HomePage hp;
    private HashMap<String,String> hmap;

    @BeforeClass
    public void testHomepage() throws IOException, FileNotFoundException
    {
        System.out.println("HomePageTest**"+this.getClass().getSimpleName());
        hmap= getTestData.readExcel(this.getClass().getSimpleName());
        hp = new HomePage(driver);
    }

    @Test
    public void checkTitle() {
        String expectedOutput=hmap.get("Title");
        System.out.println("**"+expectedOutput);
        String methodOutput=hp.getTitle(driver);
        Assert.assertEquals(methodOutput,expectedOutput);
    }

    @Test
    public  void checkToolTipTextAndLink(){
        String expectedOutput=hmap.get("ToolTipText")+"---"+hmap.get("ToolTipLink");
        String methodOutput=hp.getToolTipText(driver);
        Assert.assertEquals(methodOutput,expectedOutput);
    }


    @Test
    public  void checkFooter(){
        String methodOutput=hp.getFooterMessage(driver);

        //ref is a random generated number at server side
        // which is unique so appending to expected output
        String ref=methodOutput.substring(methodOutput.lastIndexOf(" ") + 1);
        String expectedOutput=hmap.get("CopyrightMsg")+"---"+hmap.get("FooterMsg")+ref;

        Assert.assertEquals(methodOutput,expectedOutput );
    }

   @Test
    public void checkDropDown(){
        String[] expectedOutput= hmap.get("DropDown").split(":");
        String[] methodOutput=hp.selectDropDown(driver);
        Assert.assertEquals(methodOutput,expectedOutput);
    }

    @Test
    public  void footerLinks(){
        ArrayList<String> expectedOutput=new ArrayList<>(Arrays.asList(hmap.get("footerHelpLinksTabs").split(":")));
        ArrayList<String> methodOutput=hp.footerLinks(driver);
        Assert.assertEquals(methodOutput,expectedOutput);
    }

    @Test
    public void feedBack(){
        hp.beforeTest(driver,hmap.get("ToolTipLink"));
       String methodOutput= hp.feedback(driver);
       Assert.assertTrue(methodOutput.contains(hmap.get("feedBackText")));
       hp.beforeTest(driver,hmap.get("ToolTipLink"));
    }

    @Test
    public void validSignup() throws InterruptedException{
        String email=hmap.get("EmailForSignUp");
        String methodOutput=hp.signupNewsletters(driver,email);
        System.out.println(methodOutput);
        Assert.assertTrue(methodOutput.contains(hmap.get("validSignUpText")));
    }

    @Test
    public void invalidSignup() throws InterruptedException{
        String email="mrgs";
        String methodOutput=hp.signupNewsletters(driver,email);
        Assert.assertTrue(methodOutput.contains(hmap.get("invalidSignUpText")));
    }

    @Test
    public void socialMediaIcons(){
        String[] items=hmap.get("SocialMediaIcon").split(":");
        Map<String,String> methodOutput=hp.socialMediaIcons(driver);
        for (String str:items) {
            String strlink="https://www."+str+".com/walmart";
            if(str.equals("twitter"))  Assert.assertTrue(methodOutput.get(str).contains("https://twitter.com/Walmart"));
            else if(str.equals("Walmart mobile apps")) Assert.assertTrue(methodOutput.get(str).contains("https://www.walmart.com/cp/walmart-mobile-app"));
            else if(str.equals("youtube")) Assert.assertTrue(methodOutput.get(str).contains("https://www.youtube.com/user/Walmart"));
            else
            {
               Assert.assertTrue(methodOutput.get(str).contains(strlink));
            }
        }
    }

    @Test
    public void checkTabs(){
        ArrayList<String> actualOutput=new ArrayList<>(Arrays.asList(hmap.get("HeaderTabs").split(":")));
        ArrayList<String> methodOutput=hp.getTabs(driver);
        Assert.assertEquals(methodOutput,actualOutput);
    }


}
