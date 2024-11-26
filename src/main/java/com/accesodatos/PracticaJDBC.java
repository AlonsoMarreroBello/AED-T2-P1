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

	/**
	 * The function `doesEmployeeExists` checks if an employee with a given ID
	 * exists in the database and
	 * returns the employee object if found.
	 * 
	 * @param idEmployee The `idEmployee` parameter is a unique identifier used to
	 *                   search for an employee
	 *                   in the database. The method `doesEmployeeExists` attempts
	 *                   to retrieve an employee with the given
	 *                   `idEmployee` from the database using the
	 *                   `EMPLOYEE_SERVICE.getEmployee(idEmployee)` method. If the
	 *                   employee is found
	 * @return The method `doesEmployeeExists` is returning an `Employee` object.
	 */
	private static Employee doesEmployeeExists(long idEmployee) {

		Employee employee = null;

		try {

			employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);

		} catch (SQLException e) {
			System.err.println("ID " + idEmployee + " no existe en la base de datos !!!");
		}

		return employee;
	}

	/**
	 * The function `askEmployeeData` prompts the user to input employee information
	 * such as name, last
	 * name, email, and salary, ensuring that a valid salary is entered before
	 * creating and returning an
	 * `Employee` object.
	 * 
	 * @return An Employee object with the data provided by the user, including
	 *         name, last name, email,
	 *         and salary.
	 */
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
				System.err.println("Intoduzca un salario valido. ej: 1300");
			}
			if (salary != -1) {
				keepAsking = false;
			}
		}

		return new Employee(name, lastName, email, salary);

	}

	/**
	 * The function `addEmployee` adds a new employee by getting employee data and
	 * creating the employee
	 * using a service, handling any SQL exceptions.
	 */
	static void addEmployee() {

		Employee employee = askEmployeeData();

		try {
			EMPLOYEE_SERVICE.createEmployee(employee);
			System.out.println("Empleado registrado correctamente...");
		} catch (SQLException e) {
			System.err.println("No se ha podido añadir al empleado.");
		}
	}

	/**
	 * The function `updateEmployee` prompts the user to input an employee ID,
	 * retrieves the existing
	 * employee data, allows the user to update the employee information, and then
	 * updates the employee
	 * record in the database.
	 */
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

	/**
	 * The `searchEmployee` function prompts the user to input an employee ID,
	 * retrieves the employee
	 * information from a service, and prints the employee details, handling a
	 * SQLException if the
	 * employee is not found.
	 */
	static void searchEmployee() {
		try {

			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();

			Employee employee = EMPLOYEE_SERVICE.getEmployee(idEmployee);

			employee.printInfo();

		} catch (SQLException e) {
			System.err.println("Empleado no encontrado");
		}
	}

	/**
	 * The function `deleteEmployee` prompts the user to enter an employee ID,
	 * checks if the employee
	 * exists, confirms deletion with the user, and then deletes the employee if
	 * confirmed.
	 */
	static void deleteEmployee() {

		try {

			System.out.println("Introduzca el ID:");
			long idEmployee = SCANNER.nextLong();

			Employee employee = doesEmployeeExists(idEmployee);

			employee.printInfo();
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

	/**
	 * The `addProject` function prompts the user to input a name and description
	 * for a project, creates a
	 * new `Project` object, and attempts to add it to a database using
	 * `PROJECT_SERVICE.createProject`,
	 * handling any potential `SQLException` that may occur.
	 */
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
		}
	}

	/**
	 * The function `addProjectToEmployee` allows a user to associate a project with
	 * an employee by
	 * entering their respective IDs.
	 */
	static void addProjectToEmployee() {

		long idEmployee = (long) -1;
		long idProject = (long) -1;
		Employee employee = null;

		while (employee == null) {
			System.out.print("Introduzca el ID del empleado (0 para salir) : ");
			idEmployee = SCANNER.nextLong();
			if (idEmployee == 0) {
				break;
			}
			employee = doesEmployeeExists(idEmployee);
		}

		while (employee != null) {

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

	/**
	 * The function `batchInsertProjects` creates a list of projects and attempts to
	 * insert them into a
	 * database using a service, handling any potential SQL exceptions.
	 */
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

	/**
	 * The `printMenu` function displays a menu with options for managing employees
	 * and projects.
	 */
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

	/**
	 * The function `askToContinue` prompts the user to press Enter to continue and
	 * waits for user input.
	 */
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
					System.out.println("Gracias por usar nuestra app.");
					askToContinue();
					System.exit(0);
					break;
				}
				default:
					System.err.println();
			}
		}
	}
}
