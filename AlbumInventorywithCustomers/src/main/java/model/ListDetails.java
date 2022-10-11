package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * @author peterjohnson - pmjohnson5
 * CIS175-Fall 2022
 * October 2, 2022
 */

@Entity
public class ListDetails {
	@Id
	@GeneratedValue
	private int id;
	private String listName;
	private LocalDate tripDate;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Customer customer;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<AlbumList> listOfAlbums;
	
	public ListDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ListDetails(int id, String listName, LocalDate tripDate, Customer customer, List<AlbumList> listOfAlbums) {
		super();
		this.id = id;
		this.listName = listName;
		this.tripDate = tripDate;
		this.customer = customer;
		this.listOfAlbums = listOfAlbums;
	}
	public ListDetails(String listName, LocalDate tripDate, Customer customer, List<AlbumList> listOfAlbums) {
		super();
		this.listName = listName;
		this.tripDate = tripDate;
		this.customer = customer;
		this.listOfAlbums = listOfAlbums;
	}
	public ListDetails(String listName, LocalDate tripDate, Customer customer) {
		super();
		this.listName = listName;
		this.tripDate = tripDate;
		this.customer = customer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public LocalDate getTripDate() {
		return tripDate;
	}
	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<AlbumList> getListOfAlbums() {
		return listOfAlbums;
	}
	public void setListOfAlbums(List<AlbumList> listOfAlbums) {
		this.listOfAlbums = listOfAlbums;
	}
	@Override
	public String toString() {
		return "ListDetails [id=" + id + ", listName=" + listName + ", tripDate=" + tripDate + ", customer=" + customer
				+ ", listOfItems=" + listOfAlbums + "]";
	}

}

