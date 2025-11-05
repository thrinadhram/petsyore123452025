package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {
	
	@DataProvider(name = "Data")
	public String[][] getalldata() throws IOException{
		//System.getProperty("user.dir") is a current project navigation
		String path=System.getProperty("user.dir")+"//testdata//usertestdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rownum=xl.getRowCount("Sheet1");//row count
		int colcount=xl.getcellcount("Sheet1",1);//1 is row number under cell count
		String apidata[][]=new String [rownum][colcount];
		for(int i=1; i<=rownum; i++) {
			for(int j=0; j<colcount; j++) {
				apidata[i-1][j]=xl.getcelldata("Sheet1",i,j);
				
			}
		}
		
		
		return apidata;
	}
	@DataProvider(name="UserNames")
	public String[] getusernames() throws IOException {
		String path=System.getProperty("user.dir")+"//testdata//usertestdata.xlsx";
		XLUtility xl=new XLUtility(path);
		int rownum=xl.getRowCount("Sheet1");
		String apidata[]=new String [rownum];
		for(int i=1; i<=rownum; i++) {
			apidata[i-1]=xl.getcelldata("Sheet1",i,1);
		}
		return apidata;
		
	}

}
