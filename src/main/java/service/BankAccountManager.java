package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import account.*; 
import exceptions.*;

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
	@Autowired 
	private TransferService transferer; 
	

	
	public void openHighCreditAccount(int accountNumber, String customerName, double balance, int ratio, int grade) throws IllegalArgumentException,  DuplicateAccountException{
		 highCreditAccountOpener.openHighCreditAccount(accountNumber, customerName, balance, ratio, grade);
	}
	
	public void openNormalAccount(int accountNumber, String customerName, double balance, int ratio) throws IllegalArgumentException, DuplicateAccountException {
		normalAccountOpener.openNormalAccount(accountNumber, customerName, balance, ratio);
	}
	
    public void deposit(int accountNumber, double amount) throws IllegalArgumentException ,InvalidAccountException {
    	depositer.deposit(accountNumber, amount);
    }
	
    public void withdraw(int accountNumber, double amount) throws IllegalArgumentException, InsufficientFundsException , InvalidAccountException {
    	withdrawer.withdraw(accountNumber, amount);
    }
    
    public BankAccount findAccount(int accountNumber) throws InvalidAccountException {
    	return accounterFinder.findAccount(accountNumber);
    }
        
    public void transfer(int fromAccountId , int toAccountId , double amount) throws IllegalArgumentException , InvalidAccountException ,InsufficientFundsException{
    	transferer.transfer(fromAccountId,toAccountId,amount);
    }
}

