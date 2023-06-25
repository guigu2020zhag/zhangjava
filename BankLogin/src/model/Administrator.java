package model;
/*
 * 管理员类
 */
public class Administrator {
	private String adminNumber;
	private String adminPwd;
	private String adminName;

	public String getAdminNumber(){
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber){
		this.adminNumber=adminNumber;
	}
	
	public String AdminPwd(){
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd){
		this.adminPwd=adminPwd;
	}
	public String AdminName(){
		return adminName;
	}
	public void setAdminName(String adminName){
		this.adminName=adminName;
	}
}
