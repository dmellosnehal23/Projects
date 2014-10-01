package Servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;

import entity.Movie;


/**
 * Servlet implementation class update
 */
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
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
		
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);	
		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		try{
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");
		String formname = request.getParameter("formname");

		int movieId = Integer.parseInt(request.getParameter("movieId"));

				if (formname.equals("updateMovie")){				
					Movie[] listMovies=proxy.updateMovieRequest(movieId);
					
					System.out.println(" update isss" + listMovies[0].getMovieName() );

					session.setAttribute("displayMovies", listMovies);
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("updateMovie.jsp");
					reqDispatcher.forward(request, response);

					

				}
				else // if save movie after updating
				{
					
					String movieName = request.getParameter("movieName");
					String movieBanner = request.getParameter("banner");
					String releaseDate = request.getParameter("releaseDate");  //// need to chnage
					float movieRent = Float.parseFloat(request.getParameter("rent"));
					String category = request.getParameter("category");				
					int copies =Integer.parseInt(request.getParameter("copies"));
					
					
					SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
					Date oldDate = (Date)formatter1.parse(releaseDate);

					boolean result =proxy.updateMovie(movieId, movieName, movieBanner, oldDate, movieRent, category, copies);
					if(result == true){
					Movie[] listMovies=proxy.displayMovies();
					session.setAttribute("displayMovies", listMovies);
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("listMovies.jsp");
					reqDispatcher.forward(request, response);

				}	
				}
				
	
	} catch (Exception e) {
		e.printStackTrace();

	}
	}
}
