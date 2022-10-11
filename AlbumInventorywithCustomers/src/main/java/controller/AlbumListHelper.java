package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.AlbumList;


/**
 * @author peterjohnson - pmjohnson5
 * CIS175-Fall 2022
 * October 2, 2022
 */

public class AlbumListHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("AlbumInventorywithCustomers");
	
	public void insertAlbum(AlbumList al) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(al);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<AlbumList>showAllAlbums() {
		
		EntityManager em = emfactory.createEntityManager();
		List<AlbumList>allAlbums = em.createQuery("SELECT i FROM AlbumList i").getResultList();
		return allAlbums;
	}
	
	public void deleteAlbum(AlbumList toDelete) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<AlbumList>typedQuery = em.createQuery("select al from AlbumList al where al.artist = :selectedArtist and al.title = :selectedTitle and al.year = :selectedYear", AlbumList.class);
		
		typedQuery.setParameter("selectedArtist", toDelete.getArtist());
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		
		typedQuery.setMaxResults(1);
		
		AlbumList result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public AlbumList searchForAlbumById(int idToEdit) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		AlbumList found = em.find(AlbumList.class, idToEdit);
		em.close();
		return found;		
	}

	public void updateAlbum(AlbumList toEdit) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();		
	}

	public List<AlbumList> searchForAlbumByArtist(String artistName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<AlbumList>typedQuery = em.createQuery("select al from AlbumList al where al.artist = :selectedArtist", AlbumList.class);
		typedQuery.setParameter("selectedArtist", artistName);
		List<AlbumList>foundAlbums = typedQuery.getResultList();
		em.close();
		return foundAlbums;		
	}

	public List<AlbumList> searchForAlbumsByTitle(String titleName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<AlbumList>typedQuery = em.createQuery("select al from AlbumList al where al.title = :selectedTitle", AlbumList.class);
		typedQuery.setParameter("selectedTitle", titleName);
		List<AlbumList>foundAlbums = typedQuery.getResultList();
		em.close();
		return foundAlbums;		
	}
	
	public List<AlbumList> searchForAlbumsByYear(String yearName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<AlbumList>typedQuery = em.createQuery("select al from AlbumList al where al.year = :selectedYear", AlbumList.class);
		typedQuery.setParameter("selectedYear", yearName);
		List<AlbumList>foundAlbums = typedQuery.getResultList();
		em.close();
		return foundAlbums;	
	}
	
	public void cleanUp() {
		emfactory.close();
	}	

}
