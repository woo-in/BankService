package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import account.BankAccount;
import account.HighCreditAccount;
import account.NormalAccount;
import exceptions.InvalidAccountTypeException;

@Component
public class BankAccountDao {

		
	// 입금 (반드시 탐색 후 호출해야 함) 
	public void updateBalancePlus(int accountNumber , double amount){
		String updateSql = "UPDATE BankAccount SET balance = balance + ? WHERE accountNumber = ?"; 
		
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement updateStmt = conn.prepareStatement(updateSql)
			) 
		{
            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, accountNumber);
            updateStmt.executeUpdate();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
	
	}
	
	// 출금 (반드시 탐색 후 호출해야 함) 
	public void updateBalanceMinus(int accountNumber , double amount){
		String updateSql = "UPDATE BankAccount SET balance = balance - ? WHERE accountNumber = ?"; 
		
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement updateStmt = conn.prepareStatement(updateSql)
			) 
		{
            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, accountNumber);
            updateStmt.executeUpdate();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
	}
	
	// 신용 신뢰 계좌 개설 
	public void insertHighCreditAccount(int accountNumber, String customerName, double balance, int ratio, int grade) {
		String insertSql = "INSERT INTO BankAccount VALUES(?,?,?,?,?,?)";
		
		try (
		        Connection conn = DBUtil.getConnection();
		        PreparedStatement insertStmt = conn.prepareStatement(insertSql)
		    ) 
		{
		        insertStmt.setInt(1 , accountNumber);
		        insertStmt.setString(2 ,customerName); 
		        insertStmt.setDouble(3 , balance); 
		        insertStmt.setInt(4 , ratio); 
		        insertStmt.setString(5, "HighCreditAccount");
		        insertStmt.setInt(6, grade);
		    
		        insertStmt.executeUpdate();
		        	        
		} catch (SQLException e) {
		        e.printStackTrace();
		}
			
	}
	
	// 보통 예금 계좌 개설 
	public void insertNormalAccount(int accountNumber, String customerName, double balance, int ratio) {
		String insertSql = "INSERT INTO BankAccount VALUES(?,?,?,?,?,?)";
		
		try (
		        Connection conn = DBUtil.getConnection();
		        PreparedStatement insertStmt = conn.prepareStatement(insertSql)
		    ) 
		{
		        insertStmt.setInt(1 , accountNumber);
		        insertStmt.setString(2 ,customerName); 
		        insertStmt.setDouble(3 , balance); 
		        insertStmt.setInt(4 , ratio); 
		        insertStmt.setString(5, "NormalAccount");
		        insertStmt.setInt(6,-1);
		    
		        insertStmt.executeUpdate();
		        	        
		} catch (SQLException e) {
		        e.printStackTrace();
		}
			
	}
	
	
	// accountNumber 로 계좌가 존재하는지 탐색 
	public boolean isAccountExist(int accountNumber) {
		String checkSql = "SELECT COUNT(*) FROM BankAccount WHERE accountNumber = ?"; 
		
		try (
		        Connection conn = DBUtil.getConnection();
		        PreparedStatement checkStmt = conn.prepareStatement(checkSql)
		    ) 
		{
		        checkStmt.setInt(1, accountNumber);
		        ResultSet rs = checkStmt.executeQuery();
		        rs.next();
		        int count = rs.getInt(1);
		        
		        if(count == 0) return false ; 
		        else return true ; 
		        
		        
		        
		} catch (SQLException e) {
		        e.printStackTrace();
		}
		
		// 실행 안함 
		return false;  
	}
	
	// 현재 잔액 탐색 (반드시 탐색후 호출) 
	public double selectBalance(int accountNumber) {
		String checkSql = "SELECT balance FROM BankAccount WHERE accountNumber = ?"; 
		
		try (
		        Connection conn = DBUtil.getConnection();
		        PreparedStatement checkStmt = conn.prepareStatement(checkSql)
		    ) 
		{
		        checkStmt.setInt(1, accountNumber);
		        ResultSet rs = checkStmt.executeQuery();
		        rs.next();    
		        return rs.getDouble(1);
		        
		} catch (SQLException e) {
		        e.printStackTrace();
		}
		
		// 실행 안함
		return 0.0; 
	}
	
	// accountNumber 를 바탕으로 계좌정보 탐색후 pass (반드시 탐색후 호출) 
	public BankAccount selectBankAccount(int accountNumber) throws InvalidAccountTypeException {
		
		String checkSql = "SELECT * FROM BankAccount WHERE accountNumber = ?"; 
		
		try (
		        Connection conn = DBUtil.getConnection();
		        PreparedStatement checkStmt = conn.prepareStatement(checkSql)
		    ) 
		{
		        checkStmt.setInt(1, accountNumber);
		        ResultSet rs = checkStmt.executeQuery();
		        rs.next();    
		        
		        // 1. 보통 계좌 
		        if(rs.getString(5).equals("NormalAccount")) {
		        	return new NormalAccount(rs.getInt(1) , rs.getString(2) , rs.getDouble(3), rs.getInt(4) , rs.getString(5));
		        }
		        
		        // 2. 신용 신뢰 계좌 
		        if(rs.getString(5).equals("HighCreditAccount")) {
		        	return new HighCreditAccount(rs.getInt(1) , rs.getString(2) , rs.getDouble(3), rs.getInt(4) ,rs.getInt(6), rs.getString(5));
		        }
		        
		        // 3. 계좌 불특정 
		        throw new InvalidAccountTypeException("계정 타입 오류\n"); 
		        
		        
		} catch (SQLException e) {
		        e.printStackTrace();
		}
		
		// 실행 안함 
		return null; 
	}
	
	
	
}
