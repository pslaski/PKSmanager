package com.example.jeedemo.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.History;

@Stateless
public class HistoryManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void addHistory(History history){
		history.setId(null);
		em.persist(history);
	}
	
	public void deleteHistory(History history){
		history = em.find(History.class, history.getId());
		em.remove(history);
	}
	
	@SuppressWarnings("unchecked")
	public List<History> getAllHistory(){
		return em.createNamedQuery("history.all").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<History> getHistoryByDate(Date date) {
		return em.createNamedQuery("history.findByDate").setParameter("hDate", date).getResultList();
	}

}
