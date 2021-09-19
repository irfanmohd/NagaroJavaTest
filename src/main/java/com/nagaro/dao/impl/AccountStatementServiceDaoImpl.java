package com.nagaro.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.nagaro.bean.AccountStatement;
import com.nagaro.constant.AccountStatementConstants;
import com.nagaro.dao.AccountStatementServiceDao;

@Repository
public class AccountStatementServiceDaoImpl implements AccountStatementServiceDao{
	
	private static final Logger logger = LoggerFactory.getLogger(AccountStatementServiceDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AccountStatement> getStatementByDateRange(Long accountId, String fromDate, String toDate) {
		
		logger.info("Start of getStatementByDateRange");
		
		List<AccountStatement> accountLists = null;
		
		try {
			accountLists = jdbcTemplate.query(AccountStatementConstants.DATE_RANGE_QUERY, new Object[]{
			fromDate,toDate,accountId}, (rs, rowNum) ->
	        new AccountStatement(
	                rs.getLong("ID"),
	                rs.getString("account_type"),
	                hashngAccountNumberByBcrypt(rs.getString("account_number")),
	                rs.getString("datefield"),
	                rs.getDouble("amount")
	          ));
		}catch(DataAccessException  e){
			e.printStackTrace();
			logger.error("JDBC SQL Exception",e.getMessage());
		}
		
		logger.info("End of getStatementByDateRange");
		return accountLists;
	}

	@Override
	public List<AccountStatement> getStatementByAmountRange(Long accountId, double fromAmount, double toAmount) {
		
		logger.info("Start of getStatementByAmountRange");
		List<AccountStatement> accountLists = null;
		
		try {
			accountLists = jdbcTemplate.query(AccountStatementConstants.AMOUNT_RANGE_QUERY, new Object[]{
			fromAmount,toAmount,accountId}, (rs, rowNum) ->
	        new AccountStatement(
	                rs.getLong("ID"),
	                rs.getString("account_type"),
	                hashngAccountNumberByBcrypt(rs.getString("account_number")),
	                rs.getString("datefield"),
	                rs.getDouble("amount")
	          ));
		}catch(DataAccessException  e){
			e.printStackTrace();
			logger.error("JDBC SQL Exception:",e.getMessage());
		}
		logger.info("End of getStatementByAmountRange");
		return accountLists;
	}

	@Override
	public List<AccountStatement> getAllThreeMonthsStatement(Long accountId,String lastThreeMonths) {
		
		logger.info("Start of getAllThreeMonthsStatement");
		List<AccountStatement> accountLists = null;
		
		try {
			accountLists = jdbcTemplate.query(AccountStatementConstants.LAST_THREE_MONTH_QUERY, new Object[]{
			lastThreeMonths,accountId}, (rs, rowNum) ->
	        new AccountStatement(
	                rs.getLong("ID"),
	                rs.getString("account_type"),
	                hashngAccountNumberByBcrypt(rs.getString("account_number")),
	                rs.getString("datefield"),
	                rs.getDouble("amount")
	          ));
		}catch(DataAccessException  e){
			e.printStackTrace();
			logger.error("JDBC SQL Exception:",e.getMessage());
		}
		logger.info("End of getAllThreeMonthsStatement");
		return accountLists;
	}

	/**
	 * Method to Hash account number by Bcryt hashing technique
	 * @param accountNumber
	 * @return
	 */
	private String hashngAccountNumberByBcrypt(String accountNumber) {
		String generatedSecuredAccountNumberHash = BCrypt.hashpw(accountNumber, BCrypt.gensalt(12));
		return generatedSecuredAccountNumberHash;

	}
}
