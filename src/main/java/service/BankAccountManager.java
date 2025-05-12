package service;



import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountTypeException;
import exceptions.InvalidCreditGradeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import account.*; 

@Component
public class BankAccountManager {

	@Autowired
	private OpenHighCreditAccountService highCreditAccountOpener; 
	@Autowired
	private OpenNormalAccountService normalAccountOpener; 
	@Autowired
	private DepositService depositer;
	@Autowired
	private WithdrawService withdrawer; 
	@Autowired
	private FindAccountService accounterFinder; 
	

	
	public void openHighCreditAccount(int accountNumber, String customerName, double balance, int ratio, int grade) throws IllegalArgumentException, InvalidCreditGradeException , RuntimeException{
		 highCreditAccountOpener.openHighCreditAccount(accountNumber, customerName, balance, ratio, grade);
	}
	
	public void openNormalAccount(int accountNumber, String customerName, double balance, int ratio) throws IllegalArgumentException, RuntimeException {
		normalAccountOpener.openNormalAccount(accountNumber, customerName, balance, ratio);
	}
	
    public void deposit(int accountNumber, double amount) throws IllegalArgumentException ,RuntimeException {
    	depositer.deposit(accountNumber, amount);
    }
	
    public void withdraw(int accountNumber, double amount) throws IllegalArgumentException, InsufficientFundsException , RuntimeException {
    	withdrawer.withdraw(accountNumber, amount);
    }
    
    public BankAccount findAccount(int accountNumber) throws RuntimeException , InvalidAccountTypeException{
    	return accounterFinder.findAccount(accountNumber);
    }
        
}

