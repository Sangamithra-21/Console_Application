package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO extends ParentDAO {
	
	 SearchEmployeeDAO display = new SearchEmployeeDAO();

	public void reportTree(String empName) throws SQLException {
	
		
		String query = "SELECT EmployeeName,Age,Designation,ManagerName FROM Employee WHERE EmployeeName = ? ";
		     
		
		try(PreparedStatement ps = connection.prepareStatement(query))
		{
			ps.setString(1,empName);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				display.searchEmployee(rs.getString("EmployeeName"));
			}
			
			if(rs.getString("ManagerName")!=null)
			{
				reportTree(rs.getString("ManagerName"));
			}
		}
		
		
	}

}
