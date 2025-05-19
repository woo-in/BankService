package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.BankAccountDao;
import exceptions.InsufficientFundsException;

@Component
public class WithdrawService {

	// 데이터 베이스 연동 
	@Autowired 
	private BankAccountDao bankAccountDao; 
	

    // 출금 서비스 
    public void withdraw(int accountNumber, double amount) throws IllegalArgumentException, InsufficientFundsException , RuntimeException {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Negative number error");
        }
                
        // -- 하나의 트랜잭션 
        
        // 계좌가 존재하지 않음 
        if(!bankAccountDao.isAccountExist(accountNumber)) {
        	throw new RuntimeException("No such accountNumber error");
        }
        
        // 예치금이 적음 
        if(amount > bankAccountDao.selectBalance(accountNumber)) {
        	throw new InsufficientFundsException("Insufficient Funds error");
        }
        
        // 출금 
        bankAccountDao.updateBalanceMinus(accountNumber, amount);
           
        // --------------
        
        
        
    }
}
