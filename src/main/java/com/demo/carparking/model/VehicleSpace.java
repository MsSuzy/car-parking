package com.demo.carparking.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="VehicleSpace")
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "type",
	    columnDefinition = "CHARACTER VARYING(255)"
	)
public class VehicleSpace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private double width;
	
	@Column
	private double height;

	@Column
	private int price;
	
	@Column(insertable=false, updatable=false)
	private String type;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
