package com.demo.carparking.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Floor")
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy="floor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Slot> slots= new ArrayList<>();
	
	public Floor() {
		super();
	}
	
	public Floor(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
}
