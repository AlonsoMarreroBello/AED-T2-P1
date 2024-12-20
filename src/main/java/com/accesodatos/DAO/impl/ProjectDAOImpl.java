package com.accesodatos.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accesodatos.DAO.ProjectDAO;
import com.accesodatos.db.DBConnection;
import com.accesodatos.models.Project;

public class ProjectDAOImpl implements ProjectDAO {
	
	private static final String SQL_SELECT = "SELECT * FROM projects WHERE project_id = ?";
	private static final String SQL_INSERT = "INSERT INTO projects (name, description) VALUES (?,?)";
	private static final String SQL_SELECT_PROJECTS = """
			SELECT e.project_id, p.name, p.description, p.last_update FROM employee_project e INNER JOIN projects p USING (project_id) WHERE employee_id = ?
			""";

	@Override
	public Project getById(long idProject) throws SQLException {
		
		Project project = null;
		
		try (Connection conn = DBConnection.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
			
			pstmt.setLong(1, idProject);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				project = new Project();
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("name"));
				project.setDescription(rs.getString("description"));
				project.setLastUpdate(rs.getTimestamp("last_update"));								
			}
			
			rs.close();
			
		} catch (SQLException e) {
			throw new SQLException("Coulnd find the project in database");
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
		
		boolean autoCommit = true;
		
		try (Connection conn = DBConnection.getInstance().getConnection();) {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			
			try (PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
				
				for (Project project : projects) {
					pstmt.setString(1, project.getName());
					pstmt.setString(2, project.getDescription());
					
					pstmt.addBatch();
				}
				
				pstmt.executeBatch();
				conn.commit();
				
				return true;
				
			} catch (SQLException e) {
				
				if (conn != null && !conn.getAutoCommit()) {
					conn.rollback();
				}
				
				e.printStackTrace();
				throw new SQLException("Failed to insert data. Try later!", e);
			} finally {
				if (conn != null) {
					conn.setAutoCommit(autoCommit);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Cant insert data. Try again later !!!");
		}
	}

	@Override
	public List<Project> getProjectsByEmployee(long idEmployee) throws SQLException {
		
		List<Project> projects = new ArrayList<>();
		
		try (Connection conn = DBConnection.getInstance().getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_PROJECTS)) {
			
			pstmt.setLong(1, idEmployee);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				projects.add(new Project(
						rs.getLong("project_id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("last_update")));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			throw new SQLException("Failed to get projects", e);
		}
		
		return projects;
	}

}
