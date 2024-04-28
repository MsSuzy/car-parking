package com.demo.carparking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Rent_Slot")
public class RentSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Slot slot = new Slot();

	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "total_price")
	private int totalPrice;
	
	@Column(name = "utilization_time")
	private String utilizationTime;
	
	@Column(name = "delete_flg")
	private int deleteFlg;
	
	public RentSlot() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUtilizationTime() {
		return utilizationTime;
	}

	public void setUtilizationTime(String utilizationTime) {
		this.utilizationTime = utilizationTime;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
