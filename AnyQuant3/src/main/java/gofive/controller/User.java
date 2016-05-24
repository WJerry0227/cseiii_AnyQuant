package gofive.controller;

import java.io.Serializable;

public class User implements Serializable {
	private String userName;
	private String age;
	public User(String userName,String age){
		this.userName = userName;
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
//	

}
