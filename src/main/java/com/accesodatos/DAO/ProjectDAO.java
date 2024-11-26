package com.accesodatos.DAO;

import java.sql.SQLException;
import java.util.List;

import com.accesodatos.models.Project;

public interface ProjectDAO {

	/**
	 * This Java function retrieves a Project object by its unique identifier from a
	 * database and throws a
	 * SQLException if an error occurs.
	 * 
	 * @param idProject The `idProject` parameter is a unique identifier used to
	 *                  retrieve a specific
	 *                  project from a database. In this case, the `getById` method
	 *                  is expected to return a `Project`
	 *                  object corresponding to the provided `idProject`.
	 * @return An object of type Project is being returned.
	 */
	public Project getById(long idProject) throws SQLException;

	/**
	 * The insert method is used to add a new Project object to a database
	 * and may throw a
	 * SQLException.
	 * 
	 * @param project The `insert` method you provided seems to be a method for
	 *                inserting a `Project`
	 *                object into a database. The `Project` object likely represents
	 *                some kind of project entity with
	 *                attributes such as project name, description, start date, end
	 *                date, etc.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         insertion of the
	 *         Project object was successful or not.
	 */
	public boolean insert(Project project) throws SQLException;

	/**
	 * The insert method takes a list of Project objects and inserts them into a
	 * database, throwing a
	 * SQLException if an error occurs.
	 * 
	 * @param projects A list of Project objects that you want to insert into a
	 *                 database.
	 * @return The method is returning a boolean value, which indicates whether the
	 *         insertion of the list
	 *         of projects was successful or not.
	 */
	public boolean insert(List<Project> projects) throws SQLException;

	/**
	 * This function retrieves a list of projects associated with a specific
	 * employee ID from a database,
	 * and may throw a SQLException.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier for an
	 *                   employee. The method
	 *                   `getProjectsByEmployee` is expected to return a list of
	 *                   projects associated with the employee
	 *                   identified by the `idEmployee`.
	 * @return A List of Project objects is being returned.
	 */
	public List<Project> getProjectsByEmployee(long idEmployee) throws SQLException;

}
