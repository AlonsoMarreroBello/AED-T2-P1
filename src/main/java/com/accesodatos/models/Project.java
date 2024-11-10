package com.accesodatos.models;

import java.sql.Timestamp;

public class Project {

	private long id;
	private String name;
	private String description;
	private Timestamp lastUpdate;
	
	public Project() {
	}

	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Project(long id, String name, String description, Timestamp lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.lastUpdate = lastUpdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	
	
}
