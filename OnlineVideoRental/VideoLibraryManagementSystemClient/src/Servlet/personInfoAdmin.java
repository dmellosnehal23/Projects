package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;

import entity.Movie;
import entity.Person;

/**
 * Servlet implementation class personInfoAdmin
 */
public class personInfoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public personInfoAdmin() {
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
			
			long memberId = Long.parseLong(request.getParameter("memberId"));

			Person personInfo = proxy.getUserInformationWithIssuedMovies(memberId);
		//	System.out.println("fdsfd" + movieInfo.getMovieBanner());
			
			
			session.setAttribute("personInfo", personInfo);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("personInfoAdmin.jsp");
			reqDispatcher.forward(request, response);


			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
