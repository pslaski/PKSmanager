package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Route;
import com.example.jeedemo.service.RouteManager;


@SessionScoped
@Named("routeBean")
public class RouteFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Route route = new Route();
	private Route routeToUpdate = new Route();
	private Integer h1 = 0;
	private Integer h2 = 0;
	private Integer m1 = 0;
	private Integer m2 = 0;
	private ListDataModel<Route> routes = new ListDataModel<Route>();
	
	@Inject
	RouteManager rm;

	
	public Route getRouteToUpdate() {
		return routeToUpdate;
	}

	public void setRouteToUpdate(Route routeToUpdate) {
		this.routeToUpdate = routeToUpdate;
	}

	public Integer getH1() {
		return h1;
	}

	public void setH1(Integer h1) {
		this.h1 = h1;
	}

	public Integer getH2() {
		return h2;
	}

	public void setH2(Integer h2) {
		this.h2 = h2;
	}

	public Integer getM1() {
		return m1;
	}

	public void setM1(Integer m1) {
		this.m1 = m1;
	}

	public Integer getM2() {
		return m2;
	}

	public void setM2(Integer m2) {
		this.m2 = m2;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public ListDataModel<Route> getAllRoutes() {
		routes.setWrappedData(rm.getAllRoutes());
		return routes;
	}
	
	
	//ACTIONS
	
	public String addRoute() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, h1);
		cal.set(Calendar.MINUTE, m1);
		
		route.setStartTime(cal.getTime());
		
		cal.set(Calendar.HOUR_OF_DAY, h2);
		cal.set(Calendar.MINUTE, m2);

		route.setStopTime(cal.getTime());
		rm.addRoute(route);
		route = new Route();
		return "showRoutes?faces-redirect=true";
		//return null;
	}

	public String deleteRoute() {
		Route routeToDelete = routes.getRowData();
		rm.deleteRoute(routeToDelete);
		return null;
	}
	
	public String editRoute(){
		
		routeToUpdate = routes.getRowData();
		
		return "editRoute?faces-redirect=true";
	}
	
	public String updateRoute(){
		rm.editRoute(routeToUpdate);
		routeToUpdate = new Route();
		return "showRoutes?faces-redirect=true";
	}
	
	//Validators
	
	public void uniqueName(FacesContext context, UIComponent component,
			Object value) {

		String name = (String) value;
		boolean nameGit = true;
		
		if(routeToUpdate.getName() != null && 
				routeToUpdate.getName().equals(name)) nameGit = true;
		else {
			for (Route route : rm.getAllRoutes()) {
				if (route.getName().equals(name)) {
					nameGit = false;
				}
			}
		}
			if (!nameGit) {
				FacesMessage message = new FacesMessage(
						"Istnieje już trasa o takiej nazwie");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		
	}
	
	

}
