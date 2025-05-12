package service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import dao.BankAccountDao;

@Component
public class DepositService {

	
	// 데이터 베이스 연동
	@Autowired 
	private BankAccountDao bankAccountDao ; 
	
		
    // 입금 서비스 
    public void deposit(int accountNumber, double amount) throws IllegalArgumentException , RuntimeException  {
        if (amount < 0.0) {
            throw new IllegalArgumentException("negative number error");
        }

        // -- 하나의 트랜잭션 
        
        // 계좌가 존재하지 않음 
        if(!bankAccountDao.isAccountExist(accountNumber)) {
        	throw new RuntimeException("No such accountNumber");
        }
        
        // 입금 
        bankAccountDao.updateBalancePlus(accountNumber, amount);
           
        // --------------
        
        
        
        

    }
}
