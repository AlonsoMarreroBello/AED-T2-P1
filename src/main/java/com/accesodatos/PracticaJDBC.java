package com.accesodatos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.accesodatos.exceptions.ProjectNotFoundExeption;
import com.accesodatos.models.Employee;
import com.accesodatos.models.Project;
import com.accesodatos.services.EmployeeService;
import com.accesodatos.services.ProjectService;

public class PracticaJDBC {

	private static EmployeeService EMPLOYEE_SERVICE = new EmployeeService();
	private static ProjectService PROJECT_SERVICE = new ProjectService();
	private final static Scanner SCANNER = new Scanner(System.in);

	private static Employee doesEmployeeExists(Long idEmployee) {

		Employee employee = null;

		try {

			employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);

		} catch (SQLException e) {
			System.err.println("ID " + idEmployee + " no existe en la base de datos !!!");
		}

		return employee;
	}

	private static Employee askEmployeeData() {
		System.out.print("Introduzca el nombre: ");
		String name = SCANNER.nextLine();
		System.out.print("Introduzca los apellidos: ");
		String lastName = SCANNER.nextLine();
		System.out.print("Introduzca el email: ");
		String email = SCANNER.nextLine();

		double salary = -1;
		boolean keepAsking = true;
		while (keepAsking) {
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

			Employee employee = doesEmployeeExists(idEmployee);
			employee.printInfo();

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

		} catch (NullPointerException e) {

		} catch (SQLException e) {
			System.err.println("ID " + idEmployee + " no existe en la base de datos !!!");
		}

	}

	static void searchEmployee() {
		try {

			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();

			Employee employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);

			employee.printInfo();

		} catch (SQLException e) {
			System.err.println("Empleado no encontrado");
			e.printStackTrace();
		}
	}

	static void deleteEmployee() {

		try {

			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();

			Employee employee = doesEmployeeExists(idEmployee);

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

		} catch (NullPointerException e) {

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

	static void addProjectToEmployee() {

		long idEmployee = (long) -1;
		long idProject = (long) -1;
		Employee employee = null;
		
		while (employee == null) {
			System.out.print("Introduzca el ID del empleado (0 para salir) : ");
			idEmployee = SCANNER.nextLong();
			if (idEmployee == (long) 0) {
				break;
			}
			employee = doesEmployeeExists(idEmployee);
		}
		
		while (true) {

			System.out.print("Introduzca el ID del projecto (0 para salir) : ");
			idProject = SCANNER.nextLong();
			
			if (idProject == 0) {
				break;
			}

			try {

				EMPLOYEE_SERVICE.addProjectToEmployee(idEmployee, idProject);
				System.out.println("Proyecto registrado correctamente al empleado");

			} catch (ProjectNotFoundExeption e) {
				System.err.println("El proyecto con ID " + idProject + " no existe en la base de datos.");
			} catch (SQLException e) {
				System.err.println("No se ha podido añadir el proyecto");
			} 
		}
	}

	static void batchInsertProjects() {

		List<Project> projects = new ArrayList<>();
		
		projects.add(new Project("batch_1", "Descripcion batch_1"));
		projects.add(new Project("batch_2", "Descripcion batch_2"));
		projects.add(new Project("batch_3", "Descripcion batch_3"));
		projects.add(new Project("batch_4", "Descripcion batch_4"));
		projects.add(new Project("batch_5", "Descripcion batch_5"));

		try {
			PROJECT_SERVICE.createProject(projects);
			System.out.println("Los proyectos se han creado correctamente.");
		} catch (SQLException e) {
			System.err.println("No se han podido añadir los proyectos");
		}
	}
	
	public static void printMenu() {
		
		String message = """
				╔════Opciones════════════════════════════════════════╗
				║ 1.- Añadir nuevo empleado                          ║		
				║ 2.- Modificar empleado                             ║
				║ 3.- Buscar empleado                                ║
				║ 4.- Eliminar empleado                              ║
				║ 5.- Añadir nuevo proyecto                          ║
				║ 6.- Añadir proyecto a un empleado                  ║
				║ 7.- Batch de datos                                 ║
				║ 8.- Terminar el programa                           ║
				╚════════════════════════════════════════════════════╝
				Introduzca una opción del 1 al 8""";
		System.out.println(message);
	}
	
	static void askToContinue() {
		System.out.println("Pulsa enter para continuar...");
		SCANNER.nextLine();
	}

	public static void main(String[] args) {
		while (true) {
			printMenu();
			String key = SCANNER.nextLine();
			switch (key) {
			case "1": {
				addEmployee();
				askToContinue();
				break;
			}
			case "2": {
				updateEmployee();
				askToContinue();
				break;
			}
			case "3": {
				searchEmployee();
				askToContinue();
				SCANNER.nextLine();
				break;
			}
			case "4": {
				deleteEmployee();
				askToContinue();
				break;
			}
			case "5": {
				addProject();
				askToContinue();
				break;
			}
			case "6": {
				addProjectToEmployee();
				askToContinue();
				break;
			}
			case "7": {
				batchInsertProjects();
				askToContinue();
				break;
			}
			case "8": {
				System.exit(0);
				break;
			}
			default:
				
			}
			
		}
	}
}
