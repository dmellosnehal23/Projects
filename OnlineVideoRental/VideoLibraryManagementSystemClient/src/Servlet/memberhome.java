package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VideoLibrary.ServiceProxy;
import entity.Cart;
import entity.Movie;
import entity.Person;
import entity.Transactions;

/**
 * Servlet implementation class memberhome
 */
public class memberhome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberhome() {
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
		HttpSession session = request.getSession(false);	

		VideoLibrary.ServiceProxy proxy = new VideoLibrary.ServiceProxy();
		response.setContentType("text/html");
		String formname = request.getParameter("formname");
		proxy.setEndpoint("http://localhost:8080/VideoLibraryManagementSystem/services/Service");

		String membershipId1= session.getAttribute("memberId").toString();
		long membershipId = Long.parseLong(membershipId1);
		
		//String userType1= session.getAttribute("userType1").toString();

		int userType= Integer.parseInt(session.getAttribute("userType").toString());
		session.setAttribute("userType",userType);


		try {
			if(formname.equals("allMovies")){
				
				Movie[] listMovies=proxy.displayMovies();
				if (listMovies == null)
				{
					System.out.println("No Movvie");
				}
				session.setAttribute("displayMovies", listMovies);
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("homescreen.jsp");
				reqDispatcher.forward(request, response);


			}

			if(formname.equals("myAccount")){
				
				Person person = proxy.memberFromID(membershipId);
				session.setAttribute("displayaccount", person);
				
				Transactions[] issueMovieList =proxy.issuedMovieList(membershipId);		
				
				if (issueMovieList == null){
					
					System.out.println("No Movvie");
					
					session.setAttribute("issueMovieList", null);


				}
				else{

				System.out.println("Transactions" + issueMovieList.length);
				
				session.setAttribute("issueMovieList", issueMovieList);
				}
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("myaccount.jsp");
				reqDispatcher.forward(request, response);

				
			}
			if(formname.equals("myCart")){
				
				Cart[] listCart = proxy.displayCart(membershipId); ////// 
				session.setAttribute("showCart", listCart); 
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("cart.jsp");
				reqDispatcher.forward(request, response);


			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
