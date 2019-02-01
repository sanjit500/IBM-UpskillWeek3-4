package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.AddUseDb_TC2Bean;
import com.training.bean.LoginBean;
import com.training.dao.AddUserDBDAO;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {
	
	
	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<AddUseDb_TC2Bean> list = new AddUserDBDAO().getAddUser(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(AddUseDb_TC2Bean temp : list){
			Object[]  obj = new Object[7]; 
			obj[0] = temp.getusername(); 
			obj[1] = temp.getemail(); 
			obj[2] = temp.getfirstName(); 
			obj[3] = temp.getlastName();
			obj[4] = temp.getwebsite(); 
			obj[5] = temp.getpassword();
			obj[6] = temp.getrole();

			
			result[count ++] = obj; 
		}
		
		
		return result;
	}

/*	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}*/
	
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Users/IBM_ADMIN/Desktop/Selenium training/Project/Testing.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName);
		

	}
	
	
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/IBM_ADMIN/Desktop/Selenium training/Project/Testing.xlsx", "Sheet1"); 
	}
}
