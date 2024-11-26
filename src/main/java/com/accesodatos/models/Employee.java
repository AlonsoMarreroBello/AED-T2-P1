package com.accesodatos.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The `Employee` class represents an employee entity with attributes such as
 * ID, name, email, salary,
 * projects, and last update timestamp, along with methods for setting and
 * getting these attributes and
 * printing employee information.
 */
public class Employee {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private double salary;
	private List<Project> projects;
	private Timestamp lastUpdate;

	public Employee() {
		super();
		this.projects = new ArrayList<>();
	}

	public Employee(String firstName, String lastName, String email, double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.projects = new ArrayList<>();
	}

	public Employee(long id, String firstName, String lastName, String email, double salary, List<Project> projects,
			Timestamp lastUpdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.projects = projects;
		this.lastUpdate = lastUpdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", salary=" + salary + ", projects=" + projects + ", lastUpdate=" + lastUpdate + "]";
	}

	/**
	 * The `printInfo` function generates and prints formatted information about an
	 * employee, including
	 * their ID, name, email, salary, and projects.
	 */
	public void printInfo() {
		StringBuilder strb = new StringBuilder();

		int width = 71;

		strb.append("╔" + "═".repeat(width - 2) + "╗\n");
		strb.append(String.format("║ %-15s: %-50s ║\n", "ID", id));
		strb.append(String.format("║ %-15s: %-50s ║\n", "Nombre", firstName));
		strb.append(String.format("║ %-15s: %-50s ║\n", "Apellidos", lastName));
		strb.append(String.format("║ %-15s: %-50s ║\n", "Email", email));
		strb.append(String.format("║ %-15s: %-50s ║\n", "Salario", salary));
		strb.append("╚" + "═".repeat(width - 2) + "╝\n");

		if (this.projects != null && !this.projects.isEmpty()) {
			String title = " PROYECTOS ";
			int padding = (width - title.length()) / 2;

			strb.append("╔" + "═".repeat(padding - 1) + title + "═".repeat(width - padding - title.length() - 1) + "╗\n");
			for (Project project : projects) {
				strb.append(String.format("║ %-15s: %-50s ║\n", project.getName(), project.getDescription()));
			}
			strb.append("╚" + "═".repeat(width - 2) + "╝\n");
		}
		System.out.println(strb);
	}

}
