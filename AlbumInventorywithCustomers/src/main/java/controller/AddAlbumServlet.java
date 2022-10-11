package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AlbumList;

/**
 * Servlet implementation class AddAlbumServlet
 */
@WebServlet("/addAlbumServlet")
public class AddAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String artist = request.getParameter("artist");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		
		AlbumList al = new AlbumList(artist, title, year);
		AlbumListHelper dao = new AlbumListHelper();
		dao.insertAlbum(al);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
