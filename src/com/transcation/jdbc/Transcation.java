package com.transcation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Transcation {

	public static void main(String[] args) {
	
		Scanner scanner= new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/mydatabase";
		String un="root";
		String pw="root";
		String withdrawQuery="UPDATE account SET balance = balance - ? WHERE account_number=?";
		String depositeQuery = "UPDATE account SET balance = balance + ? where account_number =?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("load and register the driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection connection= DriverManager.getConnection(url,un,pw);
			System.out.println("establish the connection");
			connection.setAutoCommit(false); // changing default true to false (auto commit)
			
	
			try {
	     PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
	     PreparedStatement depositeStatement = connection.prepareStatement(depositeQuery);
	     System.out.println("please enter withdraw amount from account123");
	     withdrawStatement.setDouble(1, scanner.nextDouble());
	     withdrawStatement.setString(2,"account123");
	     
	     System.out.println("please enter deposte amount from acount234");
	     
	     depositeStatement.setDouble(1, scanner.nextDouble());
	     depositeStatement.setString(2, "acount23ada4");
	    		 
	    int roweffectedWithdraw= withdrawStatement.executeUpdate();
	    int roweffectedDeposite= depositeStatement.executeUpdate();
	    if(roweffectedWithdraw>0 && roweffectedDeposite>0) {
	    	connection.commit();
		     System.out.println("transcation successfully");
			
	    }
	    else {
	    	connection.rollback();
	    	System.out.println("transcation failed");
	    }
	     
	     	}
			catch(SQLException e) {
					System.out.println(e.getMessage());
			}
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
	}

}
