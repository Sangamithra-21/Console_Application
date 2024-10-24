package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetManagerDAO extends ParentDAO {
	
	public void getEmployees(String managerName,String deptName) throws SQLException
	{
		String query = "SELECT EmployeeName,Age,Designation FROM Employee WHERE ManagerName = ? AND Department = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(query))
		{
			ps.setString(1,managerName);
			ps.setString(2, deptName);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("Employee Name : "+rs.getString("EmployeeName"));
				System.out.println("Employee Age  : "+rs.getString("Age"));
				System.out.println("Designation   : "+rs.getString("Designation"));
				System.out.println("------------------------------------------------");
			}
			
			
		}
	}

}
