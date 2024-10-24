package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchEmployeeDAO extends ParentDAO{
	
	public void searchEmployee(String name) throws SQLException {
	
	String query = "SELECT EmployeeName,Age,Designation,Department,ManagerName FROM Employee WHERE EmployeeName = ?";
	
	try(PreparedStatement ps = connection.prepareStatement(query))
	{
		ps.setString(1,name);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
		System.out.println("Employee Name : "+rs.getString("EmployeeName"));
		System.out.println("Employee Age  : "+rs.getInt("Age"));
		System.out.println("Designation   : "+rs.getString("Designation"));
		System.out.println("Department    : "+rs.getString("Department"));
		if(rs.getString("ManagerName")!=null) {
		System.out.println("Manager       : "+rs.getString("ManagerName"));
		}
		 System.out.println("----------------------------------------------");
		}
		else
		{
			System.out.println("No such Employee Found...!");
		}
	}
	}

}
