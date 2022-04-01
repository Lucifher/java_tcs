package com.pichincha.bcb.gestiondecuentes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.bcb.gestiondecuentes.dto.AccountDto;
import com.pichincha.bcb.gestiondecuentes.entity.AccountEntity;
import com.pichincha.bcb.gestiondecuentes.repository.AccountRepository;
import com.pichincha.bcb.gestiondecuentes.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountEntity> getAllAccounts() {
		List<AccountEntity> accountEntities = (List<AccountEntity>) accountRepository.findAll();

		/*
		 * List<AccountDto> accountDtos = new ArrayList<>();
		 * 
		 * accountEntities.stream().forEach((client) -> {
		 * accountDtos.add(client.getAsTo()); });
		 */

		return accountEntities;
	}

	@Override
	public AccountEntity readAcountWithNumber(String accountNumber) throws Exception {

		List<AccountEntity> accountEntities = accountRepository.findAllByAccountNumber(accountNumber);

		if (accountEntities == null) {
			throw new Exception("Account Not Found");
		}
		if (accountEntities.size() != 1) {
			throw new Exception("Account Not Found");
		}

		return accountEntities.get(0);
	}

	@Override
	public AccountEntity createAccount(AccountEntity accountEntity) {
		
		accountEntity.setAllMovementsWhereSource(null);
		accountEntity.setAllMovementsWhereDestination(null);


		AccountEntity accountEntityAfter = accountRepository.save(accountEntity);
		return accountEntityAfter;
	}

	@Override
	public AccountEntity updateAccount(Integer accountId, AccountEntity accountEntity) throws Exception {

		AccountEntity accountEntityDb = accountRepository.findById(accountId).orElse(null);

		if (accountEntityDb == null) {
			throw new Exception("Account Not Found");
		}
		
		accountEntityDb.setAccountNumber(accountEntity.getAccountNumber());
		accountEntityDb.setAccountBank(accountEntity.getAccountBank());
		accountEntityDb.setAccountType(accountEntity.getAccountType());
		
		

		AccountEntity accountEntityAfter = accountRepository.save(accountEntityDb);
		return accountEntityAfter;
	}

}