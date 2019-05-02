package com.wbl.test;

import com.wbl.base.BasePageTest;
import com.wbl.common.getTestData;
import com.wbl.page.SearchFlow;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class SearchTest extends BasePageTest {
    private SearchFlow sf;
    private  HashMap<String,String> hmap;

    @BeforeClass
    public void testSearchFlow() throws IOException,FileNotFoundException{
        hmap=getTestData.readExcel(this.getClass().getSimpleName());
        sf=new SearchFlow(driver);
    }


    @Test
    public void searchAndValidate(){
        sf.beforeTest(driver,hmap.get("url"));
        String item=hmap.get("item");
        String methodOutput= sf.searchValidate(driver,item);
        System.out.println(methodOutput);
        Assert.assertTrue(methodOutput.contains(item));
    }

    @Test
    public void addToCardFirstElementAndValidate(){
        sf.beforeTest(driver,hmap.get("url"));
        String methodOutput=sf.addToCartFirstItem(driver,hmap.get("item"));
        Assert.assertEquals(methodOutput,"Success");
    }
}
