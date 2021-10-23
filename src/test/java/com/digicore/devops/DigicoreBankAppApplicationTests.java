package com.digicore.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.exceptions.FinancialRestrictionException;
import com.digicore.devops.exceptions.InvalidAccountException;
import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.AccountStub;
import com.digicore.devops.models.BasicInfo;
import com.digicore.devops.utilities.AccountUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DigicoreBankAppApplicationTests {
	@Autowired
	private AccountUtil accountUtil;
	@Autowired
	private ConfigProcessor prop;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
//	@BeforeEach
//	public void init() {
//		log.info("--->> Encode " + encoder.encode("1234"));
//	}

	@Test
	public void generateAccountTest() {
		String accountNumber1 = accountUtil.generateAccountNumber();
		String accountNumber2 = accountUtil.generateAccountNumber();
		log.info("--->> " + accountNumber1);
		log.info("--->> " + accountNumber2);
		
		assertEquals(prop.getAccountNumberLength(), accountNumber1.length());
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	@Test
	public void generateAccountQueryDTOTest() {
		AccountQueryDTO aqDTO = accountUtil.generateAccountQueryDTO(AccountStub.getCustomers()[0]);
		
		assertNotNull(aqDTO);
		assertEquals("2314323453", aqDTO.getAccountNumber());
	}
	
	@Test()
	public void validateAcctNoLengthTest() {
		assertThrows(InvalidAccountException.class, 
				() -> accountUtil.validateAcctNoLength("345678"));
	}
	
	@Test()
	public void validateDepositAmountTest() {
		assertThrows(FinancialRestrictionException.class, 
				() -> accountUtil.validateDepositAmount(new BigDecimal(1500000)));
	}
	
	@Test()
	public void validateAmountTest() {
		assertThrows(FinancialRestrictionException.class, 
				() -> accountUtil.validateDepositAmount(new BigDecimal(0.5)));
	}
	
	@Test
	public void generateAccountNumberTest() {		
		assertNotNull(accountUtil.generateAccountNumber());
		assertEquals(10, accountUtil.generateAccountNumber().length());
	}
	
	@Test
	public void validateAccountTest() {
		assertNotNull(accountUtil.validateAccount("4314323453"));
		assertEquals("Mike", AccountStub.getCustomers()[0].getBasicInfo().getFirstName());
	}
}
