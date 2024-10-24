package Employee;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.AddEmployeeDAO;
import DAO.DisplayEmployeeDAO;
import DAO.GetManagerDAO;
import DAO.ReportDAO;
import DAO.SearchEmployeeDAO;

public class EmployeeView {

	Scanner sc = new Scanner(System.in);
	
	public void execute() throws SQLException {
		while(true)
		{
			System.out.println("-----------------------------------------------------");
			System.out.println("Employee Management");
			System.out.println("1)Add Employee");
			System.out.println("2)Print all Employee Details");
			System.out.println("3)Search Employee");
			System.out.println("4)Select Employee By Manager");
			System.out.println("5)Tree of Employee");
			System.out.println("6)Exit");
			
			System.out.println("Enter the option : ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1 :{
				addEmployee();
				break;
			}
			case 2 : {
				printAllEmployee();
				break;
			}
			case 3 : {
				searchEmployee();
				break;
			}
			case 4 : {
				searchEmployeeByManager();
				break;
			}
			case 5 : {
				reportTree();
				break;
			}
			}
		}
		
		
	}

	


	private void addEmployee() throws SQLException {
		
		System.out.println("Enter the Employee Name : ");
		String name = sc.next();
		
		System.out.println("Enter the Employee Age : ");
		int age = sc.nextInt();
		
		System.out.println("Enter the Employee Designation : ");
		String designation = sc.next();
		
		System.out.println("Enter the Employee Department : ");
		String department = sc.next();
		

		System.out.println("Enter the Manager Name : ");
		String manager = sc.next();
		
		
		
		AddEmployeeDAO addDAO = new AddEmployeeDAO();
		boolean add = addDAO.addEmployee(name,age,designation,department,manager);
		
		if(add)
		{
			System.out.println("Employee Added Successfully...!");
		}
		
		
	}
	

	private void printAllEmployee() throws SQLException {
		
		DisplayEmployeeDAO empDao = new DisplayEmployeeDAO();
		
		empDao.displayAllEmployees();
		
		
	}
	
	
    private void searchEmployee() throws SQLException {
		
    	System.out.println("Enter the Employee Name : ");
    	String empName = sc.next();
    	
    	SearchEmployeeDAO searchDAO = new SearchEmployeeDAO();
    	
    	searchDAO.searchEmployee(empName);
		
	}
    
    
    private void searchEmployeeByManager() throws SQLException {
		
    	System.out.println("Enter the Manager Name : ");
    	String managerName = sc.next();
    	
    	System.out.println("Enter the Department Name : ");
    	String deptName = sc.next();
    	
    	GetManagerDAO managerDAO = new GetManagerDAO();
    	managerDAO.getEmployees(managerName,deptName);
		
	}
    
    private void reportTree() throws SQLException {
		
    	System.out.println("Enter the Employee Name : ");
    	String empName = sc.next();
    	
    	ReportDAO report =  new ReportDAO();
    	report.reportTree(empName);
    	
		
	}

	
	
}
