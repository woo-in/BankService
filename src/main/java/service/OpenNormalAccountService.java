package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.BankAccountDao;

@Component
public class OpenNormalAccountService {

	// 데이터베이스 연동 
	@Autowired 
	private BankAccountDao bankAccountDao;
	
	    
    //보통 계좌 개설 서비스 
    public void openNormalAccount(int accountNumber, String customerName, double balance, int ratio) throws IllegalArgumentException, RuntimeException {
    	if (balance < 0.0 || ratio < 0)  {
        	//시스템 상 , 절대로 허용 불가 , 중복 체크 
            throw new IllegalArgumentException("negative number error");
        }
        	
    	// accountNumber 로 서칭 , 있다면 예외발생
    	if(bankAccountDao.isAccountExist(accountNumber)) {
    		throw new RuntimeException("Duplicate account number");  
    	}
    	  
    	 // 계좌 생성  
    	 bankAccountDao.insertNormalAccount(accountNumber, customerName, balance, ratio);
    	  	  
    }
}
