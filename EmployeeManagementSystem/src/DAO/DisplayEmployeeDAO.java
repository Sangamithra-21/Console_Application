package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayEmployeeDAO extends ParentDAO {
	
	SearchEmployeeDAO display = new SearchEmployeeDAO();
	
	
	
	public void displayAllEmployees() throws SQLException {
	
	String query = "SELECT * FROM Employee";
	
	try(PreparedStatement ps = connection.prepareStatement(query)){
		
	      ResultSet rs = ps.executeQuery();
			
		 while(rs.next())
		 {
			display.searchEmployee(rs.getString("EmployeeName"));
		 }
	}
	}

}
