package com.accesodatos.DAO;

import java.sql.SQLException;
import java.util.List;

import com.accesodatos.models.Project;

public interface ProjectDAO {

	public Project getById(long idProject) throws SQLException;
	public boolean insert(Project project) throws SQLException;
	public boolean insert(List<Project> projects) throws SQLException;
	public List<Project> getProjectsByEmployee(long idEmployee) throws SQLException;

}
