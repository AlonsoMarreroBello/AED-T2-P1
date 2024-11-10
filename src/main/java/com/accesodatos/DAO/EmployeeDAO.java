package com.accesodatos.DAO;

import java.sql.SQLException;

import com.accesodatos.models.Employee;

public interface EmployeeDAO {

	public Employee getById(long idEmployee) throws SQLException;
	public boolean insert(Employee employee) throws SQLException;
	public boolean update(long idEmployee, Employee employee) throws SQLException;
	public boolean delete(long idEmployee) throws SQLException;
	public boolean AddProjectToEmployee(long idEmployee, long idProject) throws SQLException;
	
}
