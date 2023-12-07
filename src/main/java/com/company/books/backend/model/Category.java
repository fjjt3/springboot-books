package com.company.books.backend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="categpories")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 476689723461888274L;
	
	

	public Category() {
		super();
	}
	public Category(Long id, String name, String description) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private String description;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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

	

}
