package api.utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][]getAllData() throws IOException{
		
		ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"//TestData//testdata.xlsx");
				
        int rownum=excel.getRowCount("sheet1");
        int colcount= excel.getCellCount("sheet1", 1);
        
        String apiData[][]= new String[rownum][colcount];
        
        for(int i=1; i<=rownum; i++) {
        	for(int j=0;j<colcount;j++) {
        		apiData[i-1][j]=excel.getCellData("sheet1", i, j);
        	}
        }
        return apiData;
	
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"//TestData//testdata.xlsx");
		int rownum=excel.getRowCount("sheet1");
		String apiData[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
			apiData[i-1]=excel.getCellData("sheet1", i, 1);
		}
		 return apiData;
	}
	
	

}
