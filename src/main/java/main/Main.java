package main;

import java.sql.SQLException;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import UI.ConsoleUI;
import config.AppCtx;

// 계좌 생성시 import 
//import java.util.Random;
//import dao.BankAccountDao;
//import service.OpenHighCreditAccountService;
//import service.OpenNormalAccountService;




public class Main {
	
	
	private static ApplicationContext ctx = null ;
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    	
    	ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
        // 클래스 선언과 DI 를 main 에서 해준다. 
    	
        ConsoleUI consoleUI = ctx.getBean("consoleUI" ,ConsoleUI.class);   
    
        // 반복문으로 기능 
        while(true){

            int command = consoleUI.showMenu();

            switch (command){
                case 1:
                    consoleUI.readAccountTypeInfo();
                    break;
                case 2:
                    consoleUI.readDepositInfo();
                    break;
                case 3:
                    consoleUI.readWithdrawInfo();
                    break;
                case 4:
                    consoleUI.showAccountInfo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("(1~5) 숫자를 입력해주세요!");
                    break;
            }
            
            
        }	
        
    	
    	
//    	// 계좌 생성 -- 
//    	ctx = new AnnotationConfigApplicationContext(AppCtx.class); 
//    	// 계좌 데이터베이스에 삽입하는 서비스 빈 얻어오기 
//    	OpenNormalAccountService openNormalAccountService = ctx.getBean("openNormalAccountService" ,OpenNormalAccountService.class);
//    	OpenHighCreditAccountService openHighCreditAccountService = ctx.getBean("openHighCreditAccountService" , OpenHighCreditAccountService.class);
//    	BankAccountDao bankAccountDao = ctx.getBean("bankAccountDao" , BankAccountDao.class);
//    	
//    	// 랜덤 
//    	Random random = new Random();
//    	
//    	// 무작위 계좌 갯수 
//    	int totalAccounts = 100000;
//    	
//    	
//    	 for (int i = 0; i < totalAccounts; i++) {
//             int accountNumber;
//             // 중복되지 않는 계좌번호 생성
//             do {
//                 accountNumber = 100000 + random.nextInt(900000); // 6자리 숫자
//             } while (bankAccountDao.isAccountExist(accountNumber)); // 중복이면 false 반환
//
//             String name = "User" + i;
//             double balance = Math.round((random.nextDouble() * 1_000_000) * 100.0) / 100.0; // 최대 100만원, 소수점 2자리
//             int ratio = random.nextInt(10); // 0~9%
//             
//
//             try {
//                 // 절반은 보통 예금, 절반은 신용 신뢰 계좌
//                 if (i % 2 == 0) {
//                	 openNormalAccountService.openNormalAccount(accountNumber, name, balance, ratio);
//                 } else {
//                     int grade = 1 + random.nextInt(3); // 1, 2, 3 중 하나
//                     openHighCreditAccountService.openHighCreditAccount(accountNumber, name, balance, ratio, grade);
//                 }
//             } catch (Exception e) {
//                 System.err.println("Error at index " + i + ": " + e.getMessage());
//             }
//         }
//
//         System.out.println("계좌 생성 완료");
//    	
    	 
    	
    	 
    	

    	
    	
    }
}