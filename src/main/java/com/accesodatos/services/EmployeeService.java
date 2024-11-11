package com.accesodatos.services;

import java.sql.SQLException;

import com.accesodatos.DAO.EmployeeDAO;
import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.DAO.impl.EmployeeDAOImpl;
import com.accesodatos.DAO.impl.ProjectDAOImpl;
import com.accesodatos.exceptions.ProjectNotFoundExeption;
import com.accesodatos.models.Employee;
import com.accesodatos.models.Project;

public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private ProjectDAO projectDAO = new ProjectDAOImpl();

	public Employee getEmployee(long idEmployee) throws SQLException {
		Employee employee = employeeDAO.getById(idEmployee);
		employee.setProjects(projectDAO.getProjectsByEmployee(idEmployee));
		return employee;
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

	public boolean addProjectToEmployee(long idEmployee, long idProject) throws SQLException, ProjectNotFoundExeption {

		if (projectDAO.getById(idProject) == null) {
			throw new ProjectNotFoundExeption(idProject);
		}

		return employeeDAO.AddProjectToEmployee(idEmployee, idProject);
	}

}
