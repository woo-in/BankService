package service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import dao.BankAccountDao;
import exceptions.InvalidAccountException;
import exceptions.InvalidAccountException.Role;

@Component
public class DepositService {

	
	// 데이터 베이스 연동
	@Autowired 
	private BankAccountDao bankAccountDao ; 
	
		
    // 입금 서비스 
	@Transactional
    public void deposit(int accountNumber, double amount) throws IllegalArgumentException , InvalidAccountException  {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Negative number error");
        }

        // 계좌가 존재하지 않음 
        if(!bankAccountDao.isAccountExist(accountNumber)) {
        	throw new InvalidAccountException(Role.GENERAL);
        }
        // 입금 
        bankAccountDao.updateBalancePlus(accountNumber, amount);
    }
}
