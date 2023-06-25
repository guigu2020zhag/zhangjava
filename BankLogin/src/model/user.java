package model;

import java.util.Date;

public class user {
	private int custNumber;
	private String custName;
	private String custPwd;
	private String custldCard;
	private int custMoney;
	private String custDate;
	private int temporary;
	public int getTemporary() {
		return temporary;
	}
	public void setTemporary(int temporary) {
		this.temporary = temporary;
	}
	public int getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(int custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPwd() {
		return custPwd;
	}
	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}
	public String getCustldCard() {
		return custldCard;
	}
	public void setCustldCard(String custldCard) {
		this.custldCard = custldCard;
	}
	public int getCustMoney() {
		return custMoney;
	}
	public void setCustMoney(int custMoney) {
		this.custMoney = custMoney;
	}
	public String getCustDate() {
		return custDate;
	}
	public void setCustDate(String custDate) {
		this.custDate = custDate;
	}
	
}
