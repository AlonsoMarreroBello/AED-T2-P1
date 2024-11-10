package com.accesodatos;

import java.sql.SQLException;
import java.util.Scanner;

import com.accesodatos.models.Employee;
import com.accesodatos.models.Project;
import com.accesodatos.services.EmployeeService;
import com.accesodatos.services.ProjectService;

public class PracticaJDBC {
	
	static EmployeeService EMPLOYEE_SERVICE = new EmployeeService();
	static ProjectService PROJECT_SERVICE = new ProjectService();
	final static Scanner SCANNER = new Scanner(System.in);
	
	private static Employee askEmployeeData() {
		System.out.print("Introduzca el nombre: ");
		String name = SCANNER.nextLine();
		System.out.print("Introduzca los apellidos: ");
		String lastName = SCANNER.nextLine();
		System.out.print("Introduzca el email: ");
		String email = SCANNER.nextLine();
		
		double salary = -1;
		boolean keepAsking = true;
		while (keepAsking ) {
			System.out.print("Introduzca el salario: ");
			String salary_str = SCANNER.nextLine();
			try {
				salary = Double.parseDouble(salary_str);
				
			} catch (Exception e) {
				System.out.println("Intoduzca un salario valido. ej: 1300");
			}
			if (salary != -1) {
				keepAsking = false;
			}
		}
		
		return new Employee(name, lastName, email, salary);
		
	}
	
	static void addEmployee() {
		
		Employee employee = askEmployeeData();
		
		try {
			EMPLOYEE_SERVICE.createEmployee(employee);
			System.out.println("Empleado registrado correctamente...");
		} catch (SQLException e) {
			System.err.println("No se ha podido añadir al empleado.");
		} 
	}
	
	
	static void updateEmployee() {
		
		System.out.println("Introduzca el ID:");
		long idEmployee = SCANNER.nextLong();
		SCANNER.nextLine();
		
		try {
			Employee employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);
			System.out.println(employee.toString());
			
			Employee newEmployee = askEmployeeData();
			
			if (newEmployee.getFirstName() == "") {
				newEmployee.setFirstName(employee.getFirstName());
			}
			if (newEmployee.getLastName() == "") {
				newEmployee.setLastName(employee.getLastName());
			}
			if (newEmployee.getEmail() == "") {
				newEmployee.setEmail(employee.getEmail());
			}
			if (newEmployee.getSalary() == 0) {
				newEmployee.setSalary(employee.getSalary());
			}
			
			EMPLOYEE_SERVICE.updateEmployee(idEmployee, newEmployee);
			System.out.println("Empleado actualizado.");
			
		} catch (SQLException e) {
			System.err.println("ID " + idEmployee + " no existe en la base de datos !!!" );
			e.printStackTrace();
		}
		
	}
	

	static void searchEmployee() {
		try {
			
			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();
			
			Employee employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);
			
			System.out.println("EMPLEADO");
			System.out.println(employee.toString());
			
		} catch (SQLException e) {
			System.err.println("Empleado no encontrado");
		}
	}
	
	static void deleteEmployee() {
		
		try {
			
			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();
			
			Employee employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);
			
			System.out.println(employee.toString());
			System.out.print("Esta seguro de quere eliminar al empleado ? (Y/N)");
			SCANNER.nextLine();
			
			String answer = SCANNER.nextLine();
			
			if (answer.equalsIgnoreCase("Y")) {
				EMPLOYEE_SERVICE.deleteEmployee(idEmployee);
				System.out.println("Empleado eliminado satisfactoriamente");
			} else if (answer.equalsIgnoreCase("N")) {
				System.out.println("Operacion cancelada.");
			}
			
			
		} catch (SQLException e) {
			System.err.println("Empleado no encontrado");
		}
	}
	
	static void addProject() {
		
		
		System.out.print("Introduzca el nombre: ");
		String name = SCANNER.nextLine();
		System.out.print("Introduzca una descripción: ");
		String description = SCANNER.nextLine();
		
		Project project = new Project(name, description);
		
		try {
			PROJECT_SERVICE.createProject(project);
			System.out.println("Proyecto añadido");
		} catch (SQLException e) {
			System.err.println("No se ha podido añadir el proyecto.");
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		//addEmployee();
		//updateEmployee();
		//searchEmployee();
		//deleteEmployee();
		//addProject();
	}
}
