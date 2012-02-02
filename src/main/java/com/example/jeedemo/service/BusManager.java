package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Bus;


@Stateless
public class BusManager {

	@PersistenceContext
	EntityManager em;
	
	public void addBus(Bus bus){
		bus.setId(null);
		em.persist(bus);
	}
	
	public void deleteBus(Bus bus){
		bus = em.find(Bus.class, bus.getId());
		em.remove(bus);
	}
	
	public void editBus(Bus bus){
		em.merge(bus);
	}
	
	public Bus getBus(Long id){
		return em.find(Bus.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Bus> getAllBus(){
		return em.createNamedQuery("bus.all").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Bus> getWorkingBus(){
		return em.createNamedQuery("bus.working").getResultList();
	}
	
}
