package com.accesodatos.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	public void printInfo() {
		StringBuilder strb = new StringBuilder();
		
		int width = 56;
		String separator = "-".repeat(width);

		strb.append(separator);
		strb.append("\n");
		strb.append(String.format("| %-12s: %-38s |", "ID", id));
		strb.append("\n");
		strb.append(String.format("| %-12s: %-38s |", "Nombre", firstName));
		strb.append("\n");
		strb.append(String.format("| %-12s: %-38s |", "Apellidos", lastName));
		strb.append("\n");
		strb.append(String.format("| %-12s: %-38s |", "Email", email));
		strb.append("\n");
		strb.append(String.format("| %-12s: %-38s |", "Salario", salary));
		strb.append("\n");
		strb.append(separator);
		strb.append("\n");
		
		if (this.projects != null && !this.projects.isEmpty()) {
			String title = " PROYECTOS ";
		    int padding = (width - title.length()) / 2; // Espacio de relleno a cada lado del título
		    String centeredTitle = "-".repeat(padding) + title + "-".repeat(width - padding - title.length());
		    
		    strb.append(centeredTitle);
		    strb.append("\n");
			for (Project project : projects) {
				strb.append(String.format("| %-12s: %-38s |", project.getName(), project.getDescription()));
				strb.append("\n");
			}
			strb.append(separator);
			strb.append("\n");
		}
		
		System.out.println(strb);
	}
	
}
