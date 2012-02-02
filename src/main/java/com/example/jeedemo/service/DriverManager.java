package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Driver;

@Stateless
public class DriverManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void addDriver(Driver driver){
		driver.setId(null);
		em.persist(driver);
	}
	
	public void deleteDriver(Driver driver){
		driver = em.find(Driver.class, driver.getId());
		em.remove(driver);
	}
	
	public void editDriver(Driver driver){
		em.merge(driver);
	}
	
	public Driver getDriver(Long id){
		return em.find(Driver.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> getAllDrivers(){
		return em.createNamedQuery("driver.all").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> getFreeDrivers(){
		return em.createNamedQuery("driver.freeDrivers").getResultList();
	}
	

}
