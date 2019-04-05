package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbExcepetion;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			
			
			conn = DB.getConnetion();
			conn.setAutoCommit(false); // espera até que o valor seja verdadeiro para completar as querys
			st = conn.createStatement();	
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId= 1");
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId= 2");
			
			conn.commit();
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbExcepetion("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbExcepetion("Error  trying to rollback! Caused by: " + e1.getMessage());
			}
			
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnetion();
		}
		
	}

}
