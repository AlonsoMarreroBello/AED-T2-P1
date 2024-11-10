package com.accesodatos.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.accesodatos.DAO.EmployeeDAO;
import com.accesodatos.db.DBConnection;
import com.accesodatos.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final String SQL_SELECT = "SELECT * FROM employees WHERE employee_id  = ?";
	private static final String SQL_INSERT = """
			INSERT INTO employees (first_name, last_name, email, salary) VALUES (?,?,?,?)
			""";
	private static final String SQL_UPDATE = "UPDATE employees SET first_name = ? , last_name = ? , email = ? , salary = ? WHERE employee_id = ?" ;
	private static final String SQL_DELETE = "DELETE FROM employees WHERE employee_id = ?";

	@Override
	public Employee getById(long idEmployee) throws SQLException {
		
		Employee employee = new Employee();
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
			
			pstmt.setLong(1, idEmployee);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			employee.setId(rs.getLong("employee_id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setEmail(rs.getString("email"));
			employee.setSalary(rs.getDouble("salary"));
			employee.setLastUpdate(rs.getTimestamp("last_update"));
			
			rs.close();
			
		} catch (SQLException e) {
			throw new SQLException("Coulnd find the employee in database");
		}
		
		return employee;
	}

	@Override
	public boolean insert(Employee employee) throws SQLException {
		try (Connection conn = DBConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
			
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setDouble(4, employee.getSalary());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			throw new SQLException("Failed to insert data. Try later!", e);
		}
	}

	@Override
	public boolean update(long idEmployee, Employee employee) throws SQLException {
		
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);) {
			
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setDouble(4, employee.getSalary());
			pstmt.setLong(5, idEmployee);
			
			return pstmt.executeUpdate() > 0; 
			
		} catch (SQLException e) {
			throw new SQLException("Failed to insert data. Try later!", e);
		} 
	}

	@Override
	public boolean delete(long idEmployee) throws SQLException {
		Connection conn = DBConnection.getInstance().getConnection();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);) {
			
			pstmt.setLong(1, idEmployee);
			
			return pstmt.executeUpdate() > 0; 
			
		} catch (SQLException e) {
			throw new SQLException("Failed to delete data. Try later!", e);
		} 
	}

	@Override
	public boolean AddProjectToEmployee(long idEmployee, long idProject) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
