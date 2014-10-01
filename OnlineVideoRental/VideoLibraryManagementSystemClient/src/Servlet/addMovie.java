
package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Movie;

import VideoLibrary.ServiceProxy;


/**
 * Servlet implementation class addMovie
 */
public class addMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		response.setContentType("text/html");
		boolean result= false;
		HttpSession session = request.getSession(false);	

		try {

			proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");

			String movieName = request.getParameter("movieName");
			String movieBanner = request.getParameter("banner");
			String releaseDate = request.getParameter("releaseDate");  //// need to chnage
			float movieRent = Float.parseFloat(request.getParameter("rent"));
			String category = request.getParameter("category");
			
			int copies =Integer.parseInt(request.getParameter("copies"));
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date oldDate = (Date)formatter1.parse(releaseDate);


			  
			 result = proxy.createMovie(movieName, movieBanner,oldDate,movieRent,category,copies);
			

			if (result == false) {
					response.sendRedirect("addMovie.jsp"); // if problem while adding movie 
				}
			
			else {
				Movie[] listMovies=proxy.displayMovies(); // if added then direclty goes to list movies
				
				session.setAttribute("displayMovies", listMovies);
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("listMovies.jsp");
				reqDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
