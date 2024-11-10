package com.accesodatos.services;

import java.sql.SQLException;

import com.accesodatos.DAO.EmployeeDAO;
import com.accesodatos.DAO.impl.EmployeeDAOImpl;
import com.accesodatos.models.Employee;

public class EmployeeService {
	
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public Employee getEmployee(long idEmployee) throws SQLException {
		return employeeDAO.getById(idEmployee);
	}
	
	public boolean createEmployee(Employee employee) throws SQLException {
		return employeeDAO.insert(employee);
	}
	
	public boolean updateEmployee(long idEmployee, Employee employee) throws SQLException {
		return employeeDAO.update(idEmployee, employee);
	}

	public boolean deleteEmployee(long idEmployee) throws SQLException {
		return employeeDAO.delete(idEmployee);
	}
	
}
