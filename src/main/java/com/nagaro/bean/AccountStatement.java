package com.nagaro.bean;

public class AccountStatement {

	public AccountStatement(Long id, String accountType, String accountNumber, String date, Double amount) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.date = date;
		this.amount = amount;
	}
	private Long id;
	private String accountType;
	private String accountNumber;
	private String date;
	private Double amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
