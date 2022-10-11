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

import model.ListDetails;
import model.Customer;
import model.AlbumList;

/**
 * Servlet implementation class EditListDetailsServlet
 */
@WebServlet("/editListDetailsServlet")
public class EditListDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditListDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ListDetailsHelper dao = new ListDetailsHelper();
		AlbumListHelper lih = new AlbumListHelper();
		CustomerHelper sh = new CustomerHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		ListDetails listToUpdate = dao.searchForListDetailsById(tempId);
		
		String newListName = request.getParameter("listName");

		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String customerName = request.getParameter("customerName");
		//find our add the new shopper
		Customer newCustomer = sh.findCustomer(customerName);
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		try {
			//albums are selected in list to add
			String[] selectedAlbums = request.getParameterValues("allAlbumsToAdd");
			List<AlbumList> selectedAlbumsInList = new ArrayList<AlbumList>();

			for (int i = 0; i < selectedAlbums.length; i++) {
				System.out.println(selectedAlbums[i]);
				AlbumList c = lih.searchForAlbumById(Integer.parseInt(selectedAlbums[i]));
				selectedAlbumsInList.add(c);
			}
			listToUpdate.setListOfAlbums(selectedAlbumsInList);
		} catch (NullPointerException ex) {
			// no albums selected in list - set to an empty list
			List<AlbumList> selectedAlbumsInList = new ArrayList<AlbumList>();
			listToUpdate.setListOfAlbums(selectedAlbumsInList);
		}

		listToUpdate.setListName(newListName);
		listToUpdate.setTripDate(ld);
		listToUpdate.setCustomer(newCustomer);

		dao.updateList(listToUpdate);

		getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);
		
		
	}

}
