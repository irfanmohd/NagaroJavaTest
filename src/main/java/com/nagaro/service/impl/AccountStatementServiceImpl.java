package com.nagaro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagaro.bean.AccountStatement;
import com.nagaro.dao.AccountStatementServiceDao;
import com.nagaro.service.AccountStatementService;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {

	@Autowired
	private AccountStatementServiceDao accountStatementServiceDao;

	@Override
	public List<AccountStatement> getStatementByDateRange(Long accountId, String fromDate, String toDate) {

		return accountStatementServiceDao.getStatementByDateRange(accountId, fromDate, toDate);
	}

	@Override
	public List<AccountStatement> getStatementByAmountRange(Long accountId, double fromAmount, double toAmount) {

		return accountStatementServiceDao.getStatementByAmountRange(accountId, fromAmount, toAmount);
	}

	@Override
	public List<AccountStatement> getAllThreeMonthsStatement(Long accountId,String lastThreeMonths) {

		return accountStatementServiceDao.getAllThreeMonthsStatement(accountId,lastThreeMonths);
	}

}
