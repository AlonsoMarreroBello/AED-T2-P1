package com.accesodatos.DAO;

import java.sql.SQLException;

import com.accesodatos.models.Employee;

public interface EmployeeDAO {

	/**
	 * This Java function retrieves an Employee object by its unique identifier and
	 * throws a SQLException
	 * if an error occurs.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier used to
	 *                   retrieve a specific
	 *                   employee from a database.
	 * @return An Employee object with the specified ID is being returned.
	 */
	public Employee getById(long idEmployee) throws SQLException;

	/**
	 * The insert method is used to add a new Employee object to a database
	 * and may throw a
	 * SQLException.
	 * 
	 * @param employee The `insert` method you provided seems to be a method for
	 *                 inserting an `Employee`
	 *                 object into a database. The `Employee` object likely contains
	 *                 information about an employee such as
	 *                 their name, ID, department, etc.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         insertion of the
	 *         Employee object was successful or not.
	 */
	public boolean insert(Employee employee) throws SQLException;

	/**
	 * This function updates an employee record in a database based on the provided
	 * employee object and
	 * employee ID.
	 * 
	 * @param idEmployee The `idEmployee` parameter is the unique identifier of the
	 *                   employee that you want
	 *                   to update in the database.
	 * @param employee   The `employee` parameter is an object of type `Employee`
	 *                   that contains the updated
	 *                   information for an employee. This object likely includes
	 *                   fields such as name, age, department, etc.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         update operation was
	 *         successful or not.
	 */
	public boolean update(long idEmployee, Employee employee) throws SQLException;

	/**
	 * The function `delete` is used to delete an employee record based on
	 * their ID and may throw
	 * a SQLException.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier for the
	 *                   employee that you want
	 *                   to delete from the database. This method is designed to
	 *                   delete an employee record based on this
	 *                   identifier.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         deletion of the
	 *         employee with the specified ID was successful or not.
	 */
	public boolean delete(long idEmployee) throws SQLException;

	/**
	 * This function adds a project to an employee and returns a boolean value
	 * indicating success or
	 * failure, throwing a SQLException if an error occurs.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier for an
	 *                   employee in the system.
	 *                   It is used to specify which employee the project will be
	 *                   added to.
	 * @param idProject  The `idProject` parameter represents the unique identifier
	 *                   of the project that you
	 *                   want to add to an employee. This identifier is used to
	 *                   uniquely identify the project within the
	 *                   system.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         project was
	 *         successfully added to the employee or not.
	 */
	public boolean AddProjectToEmployee(long idEmployee, long idProject) throws SQLException;

}
