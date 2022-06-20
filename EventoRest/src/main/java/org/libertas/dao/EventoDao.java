package org.libertas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.libertas.pojo.Evento;


public class EventoDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoHibernate");
	EntityManager em = emf.createEntityManager();
	
	public void inserir(Evento e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	} 
	
	public List<Evento> listar(){
		Query query = em.createQuery("select e from Evento e");
		List<Evento> dados = query.getResultList();
		return dados;
	}
	public void excluir (Evento e) {
		em.getTransaction().begin();
		em.remove(em.merge(e));
		em.getTransaction().commit();
		
	}
	public void alterar (Evento e) {
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}
	public Evento consultar(int id) {
		return em.find(Evento.class, id);
		
	}
	
	

}