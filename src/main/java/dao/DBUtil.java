package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	   private static final String url = "jdbc:mariadb://localhost:3306/BankSystem";
	   private static final String user = "root";
	   private static final String password = "0205";
	
	   
	   static {
		   try {
			   Class.forName("org.mariadb.jdbc.Driver"); 
		   }catch(ClassNotFoundException e) {
			   e.printStackTrace();
		   } 
	   }
	   
	   public static Connection getConnection() throws SQLException {
		   return DriverManager.getConnection(url , user , password);
	   }
	   
	   
}
