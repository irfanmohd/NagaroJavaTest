package com.nagaro.dao;

import java.util.List;

import com.nagaro.bean.AccountStatement;

public interface AccountStatementServiceDao {

	List<AccountStatement> getStatementByDateRange(Long accountId, String fromDate, String toDate);

	List<AccountStatement> getStatementByAmountRange(Long accountId, double fromAmount, double toAmount);

	List<AccountStatement> getAllThreeMonthsStatement(Long accountId,String lastThreeMonths);

}
