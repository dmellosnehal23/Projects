package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Movie;
import entity.Person;

/**
 * Servlet implementation class searchForAdmin
 */
public class searchForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchForAdmin() {
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
		PrintWriter out = response.getWriter();
		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		response.setContentType("text/html");
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");
		HttpSession session = request.getSession(false);


		String formname = request.getParameter("formname");

		try {
			
			if (formname.equals("searchMovie"))
			{
				String searchBy = request.getParameter("searchBy"); // 
				String searchValue = request.getParameter("searchTextValue"); // 

			Movie[] listMovies=proxy.searchMovie(searchBy,searchValue);		
			
			if (listMovies == null) {
					
				response.sendRedirect("NotFoundAdmin.jsp");
			}
			else{
			session.setAttribute("displayMovies", listMovies);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("listMovies.jsp");
			reqDispatcher.forward(request, response);
			}
			}
			
			if (formname.equals("searchPerson"))
			{
				String searchBy = request.getParameter("searchBy"); // 
				String searchValue = request.getParameter("searchTextValue"); // 

			Person[] listPerson=proxy.searchPerson(searchBy,searchValue);		
			
			if (listPerson == null) {
				response.sendRedirect("NotFoundAdmin.jsp");

			}
			else{
			session.setAttribute("listAllMembers", listPerson);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("listAllMembers.jsp");
			reqDispatcher.forward(request, response);
			}
			}



	
	}
		catch(Exception E)
		{}
	
	
	}	

}
