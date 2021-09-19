package com.nagaro.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagaro.bean.AccountStatement;
import com.nagaro.constant.AccountStatementConstants;
import com.nagaro.exception.AccountStatementNotFoundException;
import com.nagaro.service.AccountStatementService;

@RestController
@RequestMapping(AccountStatementConstants.API_ACCOUNT_URL)
public class AccountStatementController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountStatementController.class);
	
	@Autowired
	private AccountStatementService accountStatementService;

	@GetMapping(path = AccountStatementConstants.GET_STATEMENT_BY_DATES_URL, produces = "application/json")
	private List<AccountStatement> getStatementByDateRange(@PathVariable("accountId") Long accountId,
			@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) throws AccountStatementNotFoundException{
		logger.info("Start of getStatementByDateRange");
		List<AccountStatement> accountLists = null;
		try {
			accountLists = accountStatementService.getStatementByDateRange(accountId, fromDate, toDate);
			
			if(accountLists != null && accountLists.size()>1) {
				return accountLists;
			}
			else {
				logger.debug("Account Statement Not Found  For This AccountId:"+accountId);
				throw new AccountStatementNotFoundException("Account Statement Not Found:");
			}
		}catch(Exception ex) {
			logger.error("Internal Server Error:",ex.getMessage());
		}
		logger.info("End of getStatementByDateRange method");
		return accountLists;
	}
	
	@GetMapping(path = AccountStatementConstants.GET_STATEMENT_BY_AMOUNT_URL,  produces = "application/json")
	private List<AccountStatement> getStatementByAmountRange(@PathVariable("accountId") Long accountId,
			@PathVariable("fromAmount") double fromAmount,
			@PathVariable("toAmount") double toAmount)  throws AccountStatementNotFoundException{
		logger.info("Start of getStatementByAmountRange");
		List<AccountStatement> accountLists = null;
		try {
			accountLists = accountStatementService.getStatementByAmountRange(accountId, fromAmount, toAmount);
			if(accountLists != null && accountLists.size()>1) {
				return accountLists;
			}
			else {
				logger.debug("Account Statement Not Found  For This AccountId:"+accountId);
				throw new AccountStatementNotFoundException("Account Statement Not Found:");
			}
		}catch(Exception ex) {
			logger.error("Internal Server Error:",ex.getMessage());
		}
		logger.info("End of getStatementByAmountRange");
		return accountLists;
	}
	
	@GetMapping(path = AccountStatementConstants.GET_STATEMENT_BY_THREE_MONTH_URL,  produces = "application/json")
	private List<AccountStatement> getStatementByUser(@RequestParam(name = "accountId") Long accountId) throws AccountStatementNotFoundException{
		
		logger.info("Start of getStatementByUser");
		List<AccountStatement> accountLists = null; 
		String lastThreeMonths = getLastThreeMonth();

		try {
			accountLists = accountStatementService.getAllThreeMonthsStatement(accountId, lastThreeMonths);
			if(accountLists != null && accountLists.size()>1) {
				return accountLists;
			}
			else {
				logger.debug("Account Statement Not Found  For This AccountId:"+accountId);
				throw new AccountStatementNotFoundException("Account Statement Not Found:");
			}
		}catch(Exception ex) {
			logger.error("Internal Server Error:",ex.getMessage());
		}
		logger.info("End of getStatementByUser");
		return accountLists;

	}

	/**
	 * This method will return last three month using java 1.8 Date and Time API
	 * 
	 * @return
	 */
	private String getLastThreeMonth() {

		LocalDate now = LocalDate.now();
		LocalDate earlierThreeMonth = now.minusMonths(3);
		String formattedDate = earlierThreeMonth.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
		return formattedDate;
	}
}
