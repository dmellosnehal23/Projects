package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;

import entity.Cart;
import entity.Movie;

/**
 * Servlet implementation class movieInfoAdmin
 */
public class movieInfoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieInfoAdmin() {
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
		String formname = request.getParameter("formname");
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");

		HttpSession session = request.getSession(false);	

		try {
			
			int movieId = Integer.parseInt(request.getParameter("movieId"));

			Movie movieInfo = proxy.getMovieInformationWithIssuedByUsers(movieId);
			System.out.println("fdsfd" + movieInfo.getMovieBanner());
			
			
			session.setAttribute("movieInfo", movieInfo);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("movieInfoAdmin.jsp");
			reqDispatcher.forward(request, response);


			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	
	}

}
