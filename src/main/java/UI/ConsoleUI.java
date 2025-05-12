package UI; 

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import account.*;
import exceptions.*;
import service.*;

@Component
public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private BankAccountManager accountManager; 

//    // 생성자로 DI 
//    public ConsoleUI(BankAccountManager accountManager){
//    	this.accountManager = accountManager; 
//    }
        
    // 메뉴 출력
    public int showMenu(){

        System.out.println("----- Menu -----");
        System.out.println("1. 계좌 개설");
        System.out.println("2. 입금");
        System.out.println("3. 출금");
        System.out.println("4. 계좌 조회");
        System.out.println("5. 프로그램 종료");
        System.out.print("선택 : ");

        // 유효성 검사 (숫자가 아닌 입력)
        try {
            int menuNumber = Integer.parseInt(scanner.nextLine());
            return menuNumber;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    // 계좌 타입 선택
    public void readAccountTypeInfo(){


        System.out.println("[계좌종류선택]");
        System.out.println("1.보통예금계좌 2.신용신뢰계좌");
        System.out.print("선택 : ");
        int accountType ;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountType = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            accountType = -1;
        }

        switch (accountType){
            case 1:
                readNormalAccountInfo();
                break;
            case 2:
                readHighCreditAccountInfo();
                break;
            default:
                System.out.println("유효하지 않은 계좌 타입 선택 입니다.");
                break;
        }
    }

    // 보통 예금 계좌 개설
    public void readNormalAccountInfo(){

        System.out.println("[보통예금계좌 개설]");

        // 이미 존재하는 계좌 ID 인지 검사
        System.out.print("계좌 ID : ");
        int accountNumber = 0;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 ID 형식 입니다.");
            return ;
        }

        
        System.out.print("이름 : ");
        String customerName = scanner.nextLine();

        System.out.print("입금액 : ");
        double balance = 0.0;
        try {
            balance = Double.parseDouble(scanner.nextLine());
            if(balance < 0.0) {
            	System.out.println("이자율은 0 보다 커야 합니다.");
            	return ;
            }
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 입금액 형식 입니다.");
            return ;
        }

        System.out.print("이자율 : ");
        int ratio = 0;
        try {
            ratio = Integer.parseInt(scanner.nextLine());
            if(ratio < 0) {
            	System.out.println("이자율은 0 보다 커야 합니다.");
            	return ;
            }
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 이자율 형식 입니다.");
            return ;
        }

        try{
            accountManager.openNormalAccount(accountNumber , customerName , balance , ratio);
        }
        catch (IllegalArgumentException e){
        	// 호출시 시스템 에러 의심 (중복체크에서 걸러짐) 
            System.out.println("입금액 or 이자율은 0 보다 커야 합니다.");
            return ;
        }
        catch(RuntimeException e) {
        	// 존재하는 계좌 
        	 System.out.println("이미 존재하는 계좌 ID 입니다.");
             return;
        }
    }

    // 신용 신뢰 계좌 개설
    public void readHighCreditAccountInfo(){
        System.out.println("[신용신뢰계좌 개설]");

        // 이미 존재하는 계좌 ID 인지 검사
        System.out.print("계좌 ID : ");
        int accountNumber = 0;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 ID 형식 입니다.");
            return ;
        }

        System.out.print("이름 : ");
        String customerName = scanner.nextLine();

        System.out.print("입금액 : ");
        double balance = 0.0;
        try {
            balance = Double.parseDouble(scanner.nextLine());
            if(balance < 0.0) {
            	System.out.println("이자율은 0 보다 커야 합니다.");
            	return ;
            }
            
            
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 입금액 형식 입니다.");
            return ;
        }
        
        

        System.out.print("이자율 : ");
        int ratio = 0;
        try {
            ratio = Integer.parseInt(scanner.nextLine());
            if(ratio < 0) {
            	System.out.println("이자율은 0 보다 커야 합니다.");
            	return ;
            }
            
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 이자율 형식 입니다.");
            return ;
        }

        System.out.print("신용등급(1toA , 2toB , 3toC): ");
        int grade = 0;
        try {
            grade = Integer.parseInt(scanner.nextLine());
            if (grade != 1 && grade != 2 && grade != 3) {
            	 System.out.println("유효한 신용등급을 입력하세요(1~3)");
                 return ;
            }
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 신용등급 형식 입니다.");
            return ;
        }


        try{
            accountManager.openHighCreditAccount(accountNumber , customerName , balance , ratio,grade);
        }
        catch (IllegalArgumentException e){
        	// 호출시 시스템 에러 의심 (중복체크에서 걸러짐) 
            System.out.println("입금액 or 이자율은 0 보다 커야 합니다.");
            return ;
        }
        catch(InvalidCreditGradeException e){
        	// 호출시 시스템 에러 의심 (중복체크에서 걸러짐) 
            System.out.println("유효한 신용등급을 입력하세요(1~3)");
            return ;
        }
        catch(RuntimeException e) {
        	// 존재하는 계좌 
        	 System.out.println("이미 존재하는 계좌 ID 입니다.");
             return;
        }
    }

    // 입금
    public void readDepositInfo(){
        System.out.println("[입금]");

        // 계좌 ID 입력 및 유효성 검사
        System.out.print("계좌 ID : ");
        int accountNumber;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 ID 형식 입니다.");
            return ;
        }

        System.out.print("입금액 : ");
        double balance = 0.0;
        try {
            balance = Double.parseDouble(scanner.nextLine());
            if(balance < 0.0) {
            	System.out.println("입금액은 0보다 커야 합니다.");
            	return; 
            }
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 입금액 형식 입니다.");
            return ;
        }

        // 입금
        try {
            accountManager.deposit(accountNumber, balance);
        } catch (IllegalArgumentException e) {
        	// 중복체크 , catch 시 시스템 에러 
            System.out.println("입금액은 0 보다 커야 합니다.");
            return ;
        } catch(RuntimeException e) {
        	System.out.println("유효하지 않은 계좌 ID 입니다.");
            return;
        }

        
        // 입금 성공 
        System.out.println("입금완료");
    }

    // 출금
    public void readWithdrawInfo(){
        System.out.println("[출금]");

        // 계좌 ID 입력 및 유효성 검사
        System.out.print("계좌 ID : ");
        int accountNumber;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 ID 형식 입니다.");
            return ;
        }

        // 출금액 입력 및 유효성 검사
        System.out.print("출금액 : ");
        double balance = 0.0;
        try {
            balance = Double.parseDouble(scanner.nextLine());
            if(balance < 0.0) {
            	System.out.println("출금액은 0보다 커야 합니다.");
            	return; 
            }
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 출금액 형식 입니다.");
            return ;
        }

        // 출금
        try {
            accountManager.withdraw(accountNumber, balance);
        }
        catch (IllegalArgumentException e) {
        	// 중복체크 , catch 시 시스템 에러 
            System.out.println("출금액은 0 보다 커야 합니다.");
            return;
        } catch (InsufficientFundsException e) {
            System.out.println("잔액이 부족합니다.");
            return;
        } catch(RuntimeException e) {
        	System.out.println("유효하지 않은 계좌 ID 입니다.");
            return;
        }
        
        // 출금성공
        System.out.println("출금완료");

    }

    // 특정 계좌 조회
    public void showAccountInfo(){

        // 계좌 ID 입력 및 유효성 검사
        System.out.print("계좌 ID : ");
        int accountNumber;
        // 유효성 검사 (숫자가 아닌 입력)
        try {
            accountNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("유효하지 않은 계좌 ID 형식 입니다.");
            return ;
        }

       BankAccount found = null;
       try {
    	   found = accountManager.findAccount(accountNumber);
       }
       catch(RuntimeException e) {
            System.out.println("유효하지 않은 계좌 ID 입니다.");
            return; 
       }
       catch(InvalidAccountTypeException e) {
    	   System.out.println("유효하지 않은 계좌 타입 입니다.");
           return;
       }
        
       System.out.print("계좌 ID : ");
       System.out.println(found.getAccountNumber());

       System.out.print("이름 : ");
       System.out.println(found.getCustomerName());

       System.out.print("잔액 : ");
       System.out.println(found.getBalance());

       System.out.print("이자율 : ");
       System.out.println(found.getRatio());

       System.out.print("계좌 종류 : ");
       if(found.getAccountType().equals("NormalAccount")) {
    	     System.out.println("보통 예금 계좌");
       }
       else if(found.getAccountType().equals("HighCreditAccount")) {
    	   	 System.out.println("신용 신뢰 계좌");
       }
      
    }

}
