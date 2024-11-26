package com.accesodatos.services;

import java.sql.SQLException;

import com.accesodatos.DAO.EmployeeDAO;
import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.DAO.impl.EmployeeDAOImpl;
import com.accesodatos.DAO.impl.ProjectDAOImpl;
import com.accesodatos.exceptions.ProjectNotFoundExeption;
import com.accesodatos.models.Employee;

/**
 * The `EmployeeService` class provides methods to interact with employee and
 * project data
 * through data access objects.
 */
public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private ProjectDAO projectDAO = new ProjectDAOImpl();

	/**
	 * This function retrieves an employee by their ID from a data access object,
	 * sets their projects
	 * using another data access object, and returns the employee.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier used to
	 *                   retrieve a specific
	 *                   employee from the database. It is used to look up an
	 *                   employee's information and related projects in
	 *                   the `getEmployee` method.
	 * @return An Employee object is being returned.
	 */
	public Employee getEmployee(long idEmployee) throws SQLException {
		Employee employee = employeeDAO.getById(idEmployee);
		if (employee == null) {
			throw new SQLException("Employee not found");
		}
		employee.setProjects(projectDAO.getProjectsByEmployee(idEmployee));
		return employee;
	}

	/**
	 * The function creates a new employee by inserting their information into a
	 * database using an
	 * EmployeeDAO object.
	 * 
	 * @param employee The "employee" parameter is an object of type Employee, which
	 *                 likely contains
	 *                 information about a specific employee such as their name, ID,
	 *                 department, and other relevant
	 *                 details. This object is being passed to the createEmployee
	 *                 method for insertion into a database
	 *                 using the employeeDAO object.
	 * @return The method `createEmployee` is returning a boolean value, which is
	 *         the result of calling
	 *         the `insert` method on the `employeeDAO` object with the `employee`
	 *         parameter.
	 */
	public boolean createEmployee(Employee employee) throws SQLException {
		return employeeDAO.insert(employee);
	}

	/**
	 * This function updates an employee record in the database using the employee's
	 * ID and details
	 * provided.
	 * 
	 * @param idEmployee The `idEmployee` parameter is the unique identifier of the
	 *                   employee that you want
	 *                   to update in the database.
	 * @param employee   The `employee` parameter in the `updateEmployee` method is
	 *                   an object of type
	 *                   `Employee`. It contains the data that needs to be updated
	 *                   for the employee with the specified
	 *                   `idEmployee`.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         update operation on
	 *         the employee with the specified ID was successful or not.
	 */
	public boolean updateEmployee(long idEmployee, Employee employee) throws SQLException {
		return employeeDAO.update(idEmployee, employee);
	}

	/**
	 * This function deletes an employee record from the database using the
	 * employee's ID.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier for the
	 *                   employee that you want
	 *                   to delete from the database. It is used to locate and
	 *                   remove the specific employee record from the
	 *                   database table.
	 * @return The method `deleteEmployee` returns a boolean value indicating
	 *         whether the deletion of the
	 *         employee with the specified `idEmployee` was successful or not.
	 */
	public boolean deleteEmployee(long idEmployee) throws SQLException {
		return employeeDAO.delete(idEmployee);
	}

	/**
	 * This function adds a project to an employee and throws an exception if the
	 * project is not
	 * found.
	 * 
	 * @param idEmployee The `idEmployee` parameter represents the unique identifier
	 *                   of the employee to
	 *                   whom you want to add a project.
	 * @param idProject  The `idProject` parameter is the unique identifier of the
	 *                   project that you want to
	 *                   add to an employee. It is used to locate the specific
	 *                   project in the database and associate it with
	 *                   the employee identified by the `idEmployee` parameter.
	 * @return The method `addProjectToEmployee` returns a boolean value indicating
	 *         whether the project
	 *         was successfully added to the employee.
	 */
	public boolean addProjectToEmployee(long idEmployee, long idProject) throws SQLException, ProjectNotFoundExeption {

		if (projectDAO.getById(idProject) == null) {
			throw new ProjectNotFoundExeption(idProject);
		}

		return employeeDAO.AddProjectToEmployee(idEmployee, idProject);
	}

}
