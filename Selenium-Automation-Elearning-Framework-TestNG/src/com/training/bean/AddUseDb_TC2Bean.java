package com.training.bean;

public class AddUseDb_TC2Bean {

		private String username;
		private String email;
		private String firstName;
		private String lastName;
		private String website;
		private String password;
		private String role;

		
		public AddUseDb_TC2Bean()
		{
			
		}
		
		public AddUseDb_TC2Bean(String username, String email, String firstName, String lastName, String website, String password, String role)
		{
			super();
			this.username = username;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.website = website;
			this.password = password;
			this.role = role;
		}

		public String getusername() {
			return username;
		}

		public void setusername(String username) {
			this.username = username;
		}

		
		public String getemail() {
			return email;
		}

		public void setemail(String email) {
			this.email = email;
		}

		
		public String getfirstName() {
			return firstName;
		}

		public void setfirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getlastName() {
			return lastName;
		}

		public void setlastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getwebsite() {
			return website;
		}

		public void setwebsite(String website) {
			this.website = website;
		}
		
		public String getpassword() {
			return password;
		}

		public void setpassword(String password) {
			this.password = password;
		}
		
		public String getrole() {
			return role;
		}

		public void setrole(String role) {
			this.role = role;
		}
		
		

}