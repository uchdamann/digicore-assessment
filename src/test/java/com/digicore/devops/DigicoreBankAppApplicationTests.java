package com.digicore.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.utilities.AccountUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DigicoreBankAppApplicationTests {
	@Autowired
	private AccountUtil accountUtil;
	@Autowired
	private ConfigProcessor prop;

	@Test
	public void generateAccountTest() {
		String accountNumber1 = accountUtil.generateAccountNumber();
		String accountNumber2 = accountUtil.generateAccountNumber();
		log.info("--->> " + accountNumber1);
		log.info("--->> " + accountNumber2);
		
		assertEquals(prop.getAccountNumberLength(), accountNumber1.length());
		assertNotEquals(accountNumber1, accountNumber2);
	}
}
