package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dao.BankAccountDao;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountException;
import exceptions.InvalidAccountException.Role;

@Component 
public class TransferService {

	// 데이터 베이스 연동
	@Autowired 
	private BankAccountDao bankAccountDao ; 

	
	// 송금 서비스 
	@Transactional	
	public void transfer(int fromAccountId , int toAccountId , double amount) throws IllegalArgumentException , InvalidAccountException ,InsufficientFundsException{
       
		if (amount < 0.0) {
            throw new IllegalArgumentException("Negative number error");
        }
		
		// SENDER 계좌 존재 안함  
        if(!bankAccountDao.isAccountExist(fromAccountId)) {
       	 throw new InvalidAccountException(Role.SENDER);
        }
		// RECEIVER 계좌 존재 안함 
        if(!bankAccountDao.isAccountExist(toAccountId)) {
          	 throw new InvalidAccountException(Role.RECEIVER);
        }
                
        // 송금액이 현재 잔액보다 큼  
        if(amount > bankAccountDao.selectBalance(fromAccountId)) {
        	throw new InsufficientFundsException("Insufficient Funds error");
        }
        
        // 업데이트 
        bankAccountDao.updateBalanceMinus(fromAccountId, amount);
        bankAccountDao.updateBalancePlus(toAccountId, amount);
      
	}
	
	
	
	
	
}
