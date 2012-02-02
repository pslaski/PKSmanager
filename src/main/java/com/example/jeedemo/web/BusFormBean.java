package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Bus;
import com.example.jeedemo.service.BusManager;

@SessionScoped
@Named("busBean")
public class BusFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bus bus = new Bus();
	private Bus busToUpdate = new Bus();
	private ListDataModel<Bus> buses = new ListDataModel<Bus>();
	
	@Inject
	private BusManager bm;

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public ListDataModel<Bus> getAllBuses() {
		buses.setWrappedData(bm.getAllBus());
		return buses;
	}
	
	
	//ACTIONS
	
	public String addBus() {
		bm.addBus(bus);
		bus = new Bus();
		return "showBuses?faces-redirect=true";
		//return null;
	}

	public String deleteBus() {
		Bus busToDelete = buses.getRowData();
		bm.deleteBus(busToDelete);
		return null;
	}
	
	public String editBus(){
		
		busToUpdate = buses.getRowData();
		
		return "editBus?faces-redirect=true";
	}
	
	public String updateBus(){
		bm.editBus(busToUpdate);
		busToUpdate = new Bus();
		return "showBuses?faces-redirect=true";
	}

}
