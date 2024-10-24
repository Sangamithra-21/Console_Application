package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeDAO extends ParentDAO{

	public boolean addEmployee(String name, int age, String designation, String department,String manager) throws SQLException {
		
		String query = "INSERT INTO Employee (EmployeeName,Age,Designation,Department,ManagerName) VALUES (?,?,?,?,?)";
		
		try(PreparedStatement ps = connection.prepareStatement(query))
		{
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, designation);
			ps.setString(4,department);
			ps.setString(5, manager);
			
			int result = ps.executeUpdate();
			return result>0;
		}
		
	}
	
	
}
