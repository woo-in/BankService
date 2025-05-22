package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import account.BankAccount;
import dao.BankAccountDao;
import exceptions.InvalidAccountException;
import exceptions.InvalidAccountException.Role;

@Component
public class FindAccountService {

	// 데이터 베이스 연동
	@Autowired 
	private BankAccountDao bankAccountDao ; 
		
	
	// 계좌 조회 , 반환 서비스 
	@Transactional
    public BankAccount findAccount(int accountNumber) throws InvalidAccountException  {

    	 
         // 계좌가 존재하지 않음 
         if(!bankAccountDao.isAccountExist(accountNumber)) {
        	 throw new InvalidAccountException(Role.GENERAL);
         }
         
         // 계좌 반환 
         return bankAccountDao.selectBankAccount(accountNumber);
    }
}

