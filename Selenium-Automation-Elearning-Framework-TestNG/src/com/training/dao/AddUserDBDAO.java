package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.AddUseDb_TC2Bean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

public class AddUserDBDAO {

	Properties properties; 
	
	public AddUserDBDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties"); //Need to change anything in sql properties?
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<AddUseDb_TC2Bean> getAddUser(){
		String sql = properties.getProperty("get.login"); 
		
		GetConnection gc  = new GetConnection(); 
		List<AddUseDb_TC2Bean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<AddUseDb_TC2Bean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				AddUseDb_TC2Bean temp = new AddUseDb_TC2Bean(); 
				temp.setusername(gc.rs1.getString(1));
				temp.setemail(gc.rs1.getString(2));
				temp.setfirstName(gc.rs1.getString(3));
				temp.setlastName(gc.rs1.getString(4));
				temp.setwebsite(gc.rs1.getString(5));
				temp.setpassword(gc.rs1.getString(6));
				temp.setrole(gc.rs1.getString(7));
				
				

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new ELearningDAO().getLogins().forEach(System.out :: println);
	}
	
	
}