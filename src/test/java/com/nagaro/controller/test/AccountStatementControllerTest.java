package com.nagaro.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nagaro.bean.AccountStatement;
import com.nagaro.controller.AccountStatementController;
import com.nagaro.service.AccountStatementService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountStatementController.class)
@WithMockUser
public class AccountStatementControllerTest {
	
	private static final String DATE_RANGE_URL = "/api/account/get/statement/by/dates/5/09.02.2020/24.03.2021";
	
	private static final String AMOUNT_RANGE_URL = "/api/account/get/statement/by/amounts/5/367.34/2434.34";
	
	private static final String THREE_MONHTS_STATEMENTS_URL = "/api/account/get/statement/by/amounts/5/367.34/2434.34";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountStatementService accountStatementService;
	
	/**
	 * This Test Case written for getStatementByDateRange method
	 * @throws Exception
	 */
	@Test
	public void getStatementByDateRangeTest() throws Exception {
		List<AccountStatement> accountListsMock = new ArrayList();
		accountListsMock.add(new AccountStatement(1l, "Saving", "38823434", "01/23/2021", 9382.3234));

		Mockito.when(
				accountStatementService.getStatementByDateRange(Mockito.any(),
						Mockito.any(),Mockito.any())).thenReturn(accountListsMock);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				DATE_RANGE_URL).accept(
				MediaType.APPLICATION_JSON);

		String expected = "[{\"id\":1,\"accountType\":\"Saving\",\"accountNumber\":\"38823434\",\"date\":\"01/23/2021\",\"amount\":9382.3234}]";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		JSONAssert.assertEquals(expected, response
				.getContentAsString(), true);
		
	}
	
	/**
	 * This Test Case written for getStatementByAmountRangeTest method
	 * @throws Exception
	 */
	@Test
	public void getStatementByAmountRangeTest() throws Exception {
		List<AccountStatement> accountLists = new ArrayList();
		accountLists.add(new AccountStatement(3l, "current", "324232323", "01/23/2021", 9382.3234));

		Mockito.when(
				accountStatementService.getStatementByAmountRange(Mockito.any(),
						Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(accountLists);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				AMOUNT_RANGE_URL).accept(
				MediaType.APPLICATION_JSON);

		String expected = "[{\"id\":3,\"accountType\":\"current\",\"accountNumber\":\"324232323\",\"date\":\"01/23/2021\",\"amount\":9382.3234}]";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();		
		JSONAssert.assertEquals(expected, response
				.getContentAsString(), false);
		
	}
	
	/**
	 * This Test Case written for getStatementByAmountRangeTest method
	 * @throws Exception
	 */
	@Test
	public void getStatementByUserTest() throws Exception {
		List<AccountStatement> accountLists = new ArrayList();
		accountLists.add(new AccountStatement(2l, "current", "456", "01/03/2021", 9382.3234));
		Mockito.when(
				accountStatementService.getAllThreeMonthsStatement(Mockito.any(),
						Mockito.anyString())).thenReturn(accountLists);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				THREE_MONHTS_STATEMENTS_URL).accept(
				MediaType.APPLICATION_JSON);

		String expected = "[{\"id\":2,\"accountType\":\"current\",\"accountNumber\":\"324232323\",\"date\":\"01/23/2021\",\"amount\":9382.3234}]";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();		
		JSONAssert.assertNotEquals(expected, response
				.getContentAsString(), true);
		
	}
	

}
