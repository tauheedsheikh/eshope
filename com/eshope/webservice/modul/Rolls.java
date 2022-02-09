package com.eshope.webservice.modul;

import java.util.Date;

public class Rolls {
	private int id;
	private String rollName;
	private double salary;
	private Date createdAt;

	public Rolls(int id, String rollName, double salary, Date createdAt) {
		super();
		this.id = id;
		this.rollName = rollName;
		this.salary = salary;
		this.createdAt = createdAt;
	}

	public Rolls() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollName() {
		return rollName;
	}

	public void setRollName(String rollName) {
		this.rollName = rollName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
