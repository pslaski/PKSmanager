package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Route;

@Stateless
public class RouteManager {

	@PersistenceContext
	EntityManager em;
	
	public void addRoute(Route route){
		route.setId(null);
		em.persist(route);
	}
	
	public void deleteRoute(Route route){
		route = em.find(Route.class, route.getId());
		em.remove(route);
	}
	
	public void editRoute(Route route){
		em.merge(route);
	}
	
	public Route getRoute(Long id){
		return em.find(Route.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Route> getAllRoutes(){
		return em.createNamedQuery("route.all").getResultList();
	}

}
