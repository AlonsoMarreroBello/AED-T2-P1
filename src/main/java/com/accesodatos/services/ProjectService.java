package com.accesodatos.services;

import java.sql.SQLException;
import java.util.List;

import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.DAO.impl.ProjectDAOImpl;
import com.accesodatos.models.Project;

/**
 * The `ProjectService` class provides methods to create projects using a
 * `ProjectDAO` implementation.
 */
public class ProjectService {

	private ProjectDAO projectDAO = new ProjectDAOImpl();

	/**
	 * The function creates a project by inserting it into the database using a
	 * ProjectDAO object.
	 * 
	 * @param project Project object containing information about the project to be
	 *                created
	 * @return The method `createProject` is returning a boolean value, which is the
	 *         result of calling the
	 *         `insert` method on the `projectDAO` object with the `project`
	 *         parameter.
	 */
	public boolean createProject(Project project) throws SQLException {
		return projectDAO.insert(project);
	}

	/**
	 * The function creates a project by inserting a list of projects into the
	 * database using a
	 * ProjectDAO.
	 * 
	 * @param projects A list of Project objects that you want to create and insert
	 *                 into the database.
	 * @return The method `createProject` is returning a boolean value, which is the
	 *         result of calling the
	 *         `insert` method on the `projectDAO` object with the `projects` list
	 *         as a parameter.
	 */
	public boolean createProject(List<Project> projects) throws SQLException {
		return projectDAO.insert(projects);
	}

}
