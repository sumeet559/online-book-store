package com.training.topaz;

public class Customer {
	private String username,password,fname,lname,email,mobile,address;
	private int flag;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password
				+ ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + ", flag="
				+ flag + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getFname()="
				+ getFname() + ", getLname()=" + getLname() + ", getEmail()="
				+ getEmail() + ", getMobile()=" + getMobile()
				+ ", getAddress()=" + getAddress() + ", getFlag()=" + getFlag()
				+ "]";
	}
	
}
