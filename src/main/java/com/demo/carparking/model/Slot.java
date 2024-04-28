package com.demo.carparking.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Slot")
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String slotNumber;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Floor floor = new Floor();

	@ManyToOne
	@JoinColumn(nullable = false)
    private VehicleSpace vehicleSpace = new VehicleSpace();;
	
	@Column
	private int status;

	@OneToMany(mappedBy="slot", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RentSlot> rentSlots= new ArrayList<>();
	
	public Slot() {
		super();
	}

	public Slot(VehicleSpace vehicleSpace, String slotNumber) {
		this.vehicleSpace = vehicleSpace;
		this.slotNumber = slotNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}
	
	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public VehicleSpace getVehicleSpace() {
		return vehicleSpace;
	}

	public void setVehicleSpace(VehicleSpace vehicleSpace) {
		this.vehicleSpace = vehicleSpace;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<RentSlot> getRentSlots() {
		return rentSlots;
	}

	public void setRentSlots(List<RentSlot> rentSlots) {
		this.rentSlots = rentSlots;
	}
}
