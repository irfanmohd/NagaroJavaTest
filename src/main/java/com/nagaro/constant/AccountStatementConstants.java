package com.nagaro.constant;

public class AccountStatementConstants {
	
	public static final String DATE_RANGE_QUERY = "SELECT act.ID,act.account_type,act.account_number, stmt.datefield,stmt.amount "
			+ "FROM account act INNER JOIN statement stmt ON act.ID = stmt.account_id where "
			+ "stmt.datefield >= ? and stmt.datefield <= ? and stmt.account_id = ?";
	
	public static final String AMOUNT_RANGE_QUERY = "SELECT act.ID,act.account_type,act.account_number, stmt.datefield,stmt.amount "
			+ "FROM account act INNER JOIN statement stmt ON act.ID = stmt.account_id where "
			+ "stmt.amount >= ? and stmt.amount <= ? and stmt.account_id = ?";
	
	public static final String LAST_THREE_MONTH_QUERY= "SELECT act.ID,act.account_type,act.account_number, stmt.datefield,stmt.amount "
			+ "FROM account act INNER JOIN statement stmt ON act.ID = stmt.account_id where "
			+ "stmt.amount >= ? and stmt.account_id = ?";

	
	public static final String API_ACCOUNT_URL = "/api/account";
	
	public static final String GET_STATEMENT_BY_DATES_URL = "/get/statement/by/dates/{accountId}/{fromDate}/{toDate}";
	
	public static final String GET_STATEMENT_BY_AMOUNT_URL = "/get/statement/by/amounts/{accountId}/{fromAmount}/{toAmount}";
	
	public static final String GET_STATEMENT_BY_THREE_MONTH_URL = "/get/last/three/month/statement";
	
	
}
