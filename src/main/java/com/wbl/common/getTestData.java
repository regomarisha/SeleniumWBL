package com.wbl.common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getTestData {
    final  static String filePath = "TestData/WalmartTestData.xlsx";


    public static  HashMap<String, String> readExcel(String sheetname)  throws FileNotFoundException, IOException{
        HashMap<String,String> hmap=new HashMap<String, String>();
        File file = new File(filePath);
        FileInputStream inStream = new FileInputStream(file);
        Workbook testWorkbook = new XSSFWorkbook(inStream);
        Sheet testSheet = testWorkbook.getSheet(sheetname);
        int rowCount = testSheet.getLastRowNum() - testSheet.getFirstRowNum() ;
        for (int i = 1; i < rowCount + 1; i++) {
            Row row = testSheet.getRow(i);
            String str="";
            for (int j = 0; j < ((Row) row).getLastCellNum(); j++) {
               str=str+row.getCell(j).getStringCellValue()+"::";
            }
            String[] map = str.split("::");
            hmap.put(map[0],map[1]);
        }
        return hmap;
    }
}
