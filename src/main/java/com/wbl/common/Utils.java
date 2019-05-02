package com.wbl.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    final String propFile="data.properties";
    public String url;
    public String browser;

    public Utils() throws FileNotFoundException, IOException{
        Properties prop=new Properties();
        prop.load(new FileReader(propFile));
        url=prop.getProperty("url");
        browser=prop.getProperty("browser");
        System.out.println("Browser : "+browser);
        System.out.println("URL : "+url);
    }
}
