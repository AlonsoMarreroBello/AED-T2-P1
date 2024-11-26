package com.accesodatos.exceptions;

/**
 * The `ProjectNotFoundException` class is a custom exception used to indicate
 * when a project
 * with a specific ID is not found.
 */
public class ProjectNotFoundExeption extends Exception {

	private static final long serialVersionUID = 1556101449546957825L;

	public ProjectNotFoundExeption(long projectId) {
		super("Project with ID " + projectId + " not found.");
	}

	public ProjectNotFoundExeption(long projectId, Throwable e) {
		super("Project with ID " + projectId + " not found.", e);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
