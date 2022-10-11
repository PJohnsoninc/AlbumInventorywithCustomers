package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Customer;

/**
 * @author peterjohnson - pmjohnson5
 * CIS175-Fall 2022
 * October 2, 2022
 */
public class CustomerHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("AlbumInventorywithCustomers");

	public void insertCustomer(Customer s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Customer> showAllCustomers(){
		EntityManager em = emfactory.createEntityManager();
		List<Customer> allCustomers = em.createQuery("SELECT s from Customer s").getResultList();
		return allCustomers;
	}
	
	public Customer findCustomer(String nameToLookUp) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Customer> typedQuery = em.createQuery("select sh from Customer sh where sh.customerName = :selectedName",Customer.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		typedQuery.setMaxResults(1);
		
		Customer foundCustomer;
		try {
			foundCustomer = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundCustomer = new Customer(nameToLookUp);
		}
		em.close();

		return foundCustomer;
	}

}
