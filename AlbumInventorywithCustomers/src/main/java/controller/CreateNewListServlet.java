package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AlbumList;
import model.Customer;
import model.ListDetails;


/**
 * Servlet implementation class CreateNewListServlet
 */
@WebServlet("/createNewListServlet")
public class CreateNewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AlbumListHelper alh = new AlbumListHelper();
		String listName = request.getParameter("listName");
		System.out.println("List Name: " + listName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String customerName = request.getParameter("customerName");
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}catch (NumberFormatException ex){
			ld = LocalDate.now();
		}
		
		String[] selectedAlbums = request.getParameterValues("allAlbumsToAdd");
		List<AlbumList> selectedAlbumsInList = new ArrayList<AlbumList>();
		
		if(selectedAlbums != null && selectedAlbums.length > 0){
			for(int i = 0; i<selectedAlbums.length; i++) {
				System.out.println(selectedAlbums[i]);
				AlbumList c = alh.searchForAlbumById(Integer.parseInt(selectedAlbums[i]));
				selectedAlbumsInList.add(c);
			}
		}
		
		Customer customer = new Customer(customerName);
		ListDetails cld = new ListDetails(listName, ld, customer);
		cld.setListOfAlbums(selectedAlbumsInList);
		ListDetailsHelper clh = new ListDetailsHelper();
		clh.insertNewListDetails(cld);
		
		System.out.println("Success!");
		System.out.println(cld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
