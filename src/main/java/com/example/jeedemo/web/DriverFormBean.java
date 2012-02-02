package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.DriverManager;


@SessionScoped
@Named("driverBean")
public class DriverFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Driver driver = new Driver();
	private Driver driverToUpdate = new Driver();
	private ListDataModel<Driver> drivers = new ListDataModel<Driver>();
	
	@Inject
	private DriverManager dm;

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	
	public ListDataModel<Driver> getAllDrivers() {
		drivers.setWrappedData(dm.getAllDrivers());
		return drivers;
	}
	
	//ACTIONS
	
	public String addDriver() {
		dm.addDriver(driver);
		driver = new Driver();
		return "showDrivers?faces-redirect=true";
		//return null;
	}

	public String deleteDriver() {
		Driver driverToDelete = drivers.getRowData();
		dm.deleteDriver(driverToDelete);
		return null;
	}
	
	public String editDriver(){
		
		driverToUpdate = drivers.getRowData();
		
		return "editDriver?faces-redirect=true";
	}
	
	public String updateDriver(){
		dm.editDriver(driverToUpdate);
		driverToUpdate = new Driver();
		return "showDrivers?faces-redirect=true";
	}

}
