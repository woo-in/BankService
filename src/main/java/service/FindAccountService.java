package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import account.BankAccount;
import dao.BankAccountDao;
import exceptions.InvalidAccountTypeException;

@Component
public class FindAccountService {

	// 데이터 베이스 연동
	@Autowired 
	private BankAccountDao bankAccountDao ; 
		
	
	// 계좌 조회 , 반환 서비스 
    public BankAccount findAccount(int accountNumber) throws RuntimeException ,InvalidAccountTypeException  {

    	 // -- 하나의 트랜잭션 
         
         // 계좌가 존재하지 않음 
         if(!bankAccountDao.isAccountExist(accountNumber)) {
         	throw new RuntimeException("No such accountNumber");
         }
         
         return bankAccountDao.selectBankAccount(accountNumber);
         
         // --------------
          
    }
	
}

