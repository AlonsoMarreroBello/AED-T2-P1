package com.accesodatos.services;

import java.sql.SQLException;
import java.util.List;

import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.DAO.impl.ProjectDAOImpl;
import com.accesodatos.models.Project;

public class ProjectService {
	
	private ProjectDAO projectDAO = new ProjectDAOImpl();
	
	public boolean createProject(Project project) throws SQLException {
		return projectDAO.insert(project);
	}
	public boolean createProject(List<Project> projects) throws SQLException {
		return projectDAO.insert(projects);
	}

}
