package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ParentDAO {
	
	protected Connection connection;
	
	public ParentDAO() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	
	}
	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	
	try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","Mithra@2112");
		
	}catch(SQLException e1)
	{
		e1.printStackTrace();
	}
	
	}

}
