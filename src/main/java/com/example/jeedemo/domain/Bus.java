package com.example.jeedemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name = "bus.working", query = "Select b from Bus b where b.status = true"),
	@NamedQuery(name = "bus.all", query = "Select b from Bus b")
})
public class Bus {
	
	private Long id;
	private String vendor;
	private String model;
	private Boolean status = true;
	private Integer seatsAmount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min = 1, max = 20)
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	@Size(min = 1, max = 30)
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getSeatsAmount() {
		return seatsAmount;
	}
	public void setSeatsAmount(Integer seatsAmount) {
		this.seatsAmount = seatsAmount;
	}
	
	@Override
	public String toString() {
		return vendor+" "+model+": "+ seatsAmount;
	}


}
