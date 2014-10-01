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
import entity.Person;
import entity.Transactions;

/**
 * Servlet implementation class myaccount
 */
public class myaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myaccount() {
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

		String membershipId1= session.getAttribute("memberId").toString();
		long membershipId = Long.parseLong(membershipId1);
		
		int userType= Integer.parseInt(session.getAttribute("userType").toString());
		session.setAttribute("userType",userType);

		try {
			if(formname.equals("updateAccount")){
				
				Person person = proxy.memberFromID( membershipId);
				session.setAttribute("displayaccount", person);
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("updatemember.jsp");
				reqDispatcher.forward(request, response);
				
			}
				if(formname.equals("updateDetails")){
					
				//	long membershipId2 = Long.parseLong(request.getParameter("membershipId")); // Email.
					String emailId = request.getParameter("email"); // Email.
					String password = request.getParameter("password"); // password.
					String firstName  = request.getParameter("fname"); // First Name.
					String lastName = request.getParameter("lname"); // Last Name
					String address = request.getParameter("address"); // Address.
					String city = request.getParameter("city"); // City
					String state = request.getParameter("state"); // State
					long zipCode = Long.parseLong(request.getParameter("zipcode")); // Zip Code.

				
					boolean result = proxy.updatePerson(membershipId, firstName, lastName, emailId, password, address, city, state, zipCode);
					
					if (result == true){
					Person person = proxy.memberFromID(membershipId);
					session.setAttribute("displayaccount", person);
					RequestDispatcher reqDispatcher = request.getRequestDispatcher("myaccount.jsp");
					reqDispatcher.forward(request, response);
					}
					else
					{
						response.sendRedirect("updatemember.jsp");					
					}
					
				}	
					if(formname.equals("returnMovie")){
						
						
					int movieId = Integer.parseInt(request.getParameter("movieId"));


					boolean result1 =	proxy.returnMovie(movieId, membershipId , userType);
					
					
					Person person = proxy.memberFromID(membershipId);
					session.setAttribute("displayaccount", person);

					
					Transactions[] issueMovieList =proxy.issuedMovieList(membershipId);
					session.setAttribute("issueMovieList", issueMovieList);

					RequestDispatcher reqDispatcher = request.getRequestDispatcher("myaccount.jsp");
					reqDispatcher.forward(request, response);

						
					}

				
				
	
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
