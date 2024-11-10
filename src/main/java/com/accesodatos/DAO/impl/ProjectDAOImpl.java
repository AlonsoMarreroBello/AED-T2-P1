package com.accesodatos.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.db.DBConnection;
import com.accesodatos.models.Employee;
import com.accesodatos.models.Project;

public class ProjectDAOImpl implements ProjectDAO {
	
	private static final String SQL_SELECT = "SELECT * FROM projects WHERE ?";
	private static final String SQL_INSERT = "INSERT INTO projects (name, description) VALUES (?,?)";

	@Override
	public Project getById(long idProject) throws SQLException {
		
		Project project = new Project();
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
			
			pstmt.setLong(1, idProject);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			project.setId(rs.getLong("employee_id"));
			project.setName(rs.getString("first_name"));
			project.setDescription(rs.getString("last_name"));
			project.setLastUpdate(rs.getTimestamp("last_update"));
			
			rs.close();
			
		} catch (SQLException e) {
			throw new SQLException("Coulnd find the employee in database");
		}
		
		return project;
	}

	@Override
	public boolean insert(Project project) throws SQLException {
		try (Connection conn = DBConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
			
			pstmt.setString(1, project.getName());
			pstmt.setString(2, project.getDescription());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			throw new SQLException("Failed to insert data. Try later!", e);
		}
	}

	@Override
	public boolean insert(List<Project> projects) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Project> getProjectsByEmployee(long idEmployee) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
